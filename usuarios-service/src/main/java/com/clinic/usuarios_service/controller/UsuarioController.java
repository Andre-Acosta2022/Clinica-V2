package com.clinic.usuarios_service.controller;
import com.clinic.usuarios_service.entity.usuarios;
import com.clinic.usuarios_service.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import java.time.LocalDateTime;
import java.util.Optional;

import com.clinic.usuarios_service.service.UsuarioService;


@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    @Autowired
    private final UsuarioRepository usuarioRepository;
    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private final UsuarioService usuarioService;

    @PostMapping("/crear")
    public ResponseEntity<usuarios> crearUsuario(@RequestParam String nombre,
                                                 @RequestParam String email,
                                                 @RequestParam String role,
                                                 @RequestParam String dni) {
        usuarios usuarioCreado = usuarioService.crearUsuario(nombre, email, role, dni);
        return ResponseEntity.ok(usuarioCreado);
    }
    @PostMapping("/activar")
    public ResponseEntity<String> activarCuenta(@RequestParam String token,
                                                @RequestParam String nuevaPassword) {
        Optional<usuarios> optUsuario = usuarioRepository.findByTokenActivacion(token);
        if (optUsuario.isEmpty()) {
            return ResponseEntity.badRequest().body("Token inválido");
        }
        usuarios usuario = optUsuario.get();

        if (usuario.getTokenExpira().isBefore(LocalDateTime.now())) {
            return ResponseEntity.badRequest().body("Token expirado");
        }

        usuario.setPassword(passwordEncoder.encode(nuevaPassword));
        usuario.setActivo(true);
        usuario.setTokenActivacion(null);
        usuario.setTokenExpira(null);
        usuarioRepository.save(usuario);

        return ResponseEntity.ok("Cuenta activada correctamente");
    }

    public ResponseEntity<String> cambiarContrasena(@RequestParam String token, @RequestParam String nuevaContrasena) {
        usuarioService.cambiarContrasena(token, nuevaContrasena);
        return ResponseEntity.ok("Contraseña cambiada exitosamente");
    }
}
