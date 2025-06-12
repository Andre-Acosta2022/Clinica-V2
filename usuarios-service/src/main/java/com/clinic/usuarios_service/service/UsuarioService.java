package com.clinic.usuarios_service.service;



import com.clinic.usuarios_service.entity.usuarios;
import com.clinic.usuarios_service.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {

        private final UsuarioRepository usuarioRepository;
        private final JavaMailSender mailSender;
        private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public usuarios crearUsuario(String nombre, String email, String role, String dni) {
        // Verificar si el correo ya está registrado
        Optional<usuarios> usuarioExistente = usuarioRepository.findByEmail(email);
        if (usuarioExistente.isPresent()) {
            throw new RuntimeException("El correo electrónico ya está registrado.");
        }

        // Verificar si el DNI ya está registrado
        Optional<usuarios> usuarioPorDni = usuarioRepository.findByDni(dni);
        if (usuarioPorDni.isPresent()) {
            throw new RuntimeException("El DNI ya está registrado.");
        }

        // Generar la contraseña temporal
        String rawPassword = generarPassword(10);  // Llama al generador de contraseña
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // Generar token y tiempo de expiración
        String token = UUID.randomUUID().toString();
        LocalDateTime expiracion = LocalDateTime.now().plusHours(24);

        // Crear el objeto usuario
        usuarios usuario = usuarios.builder()
                .nombre(nombre)
                .email(email)
                .role(role)
                .password(encodedPassword)
                .activo(false) // pendiente activación
                .tokenActivacion(token)
                .tokenExpira(expiracion)
                .build();

        // Guardar el usuario en la base de datos
        usuarioRepository.save(usuario);

        // Enviar correo con la contraseña temporal
        enviarCorreo(email, rawPassword);  // Aquí envías el correo con la contraseña temporal

        return usuario;
    }

    private void enviarCorreo(String email, String rawPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Credenciales de acceso - Clínica");
        message.setText("Su usuario ha sido creado.\n\nContraseña temporal: " + rawPassword +
                "\nPor favor, cambie su contraseña en el primer inicio de sesión.");
        mailSender.send(message);
    }
        private String generarPassword(int length) {
            String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%&*!";
            SecureRandom random = new SecureRandom();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<length; i++) {
                int idx = random.nextInt(chars.length());
                sb.append(chars.charAt(idx));
            }
            return sb.toString();
        }
    public ResponseEntity<String> cambiarContrasena(@RequestParam String email, @RequestParam String nuevaPassword) {
        Optional<usuarios> usuarioOpt = usuarioRepository.findByEmail(email);
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("El usuario no existe.");
        }

        usuarios usuario = usuarioOpt.get();
        if (!usuario.isActivo()) {
            return ResponseEntity.badRequest().body("La cuenta no está activa.");
        }

        usuario.setPassword(passwordEncoder.encode(nuevaPassword));
        usuarioRepository.save(usuario);

        return ResponseEntity.ok("Contraseña cambiada exitosamente.");
    }
}
