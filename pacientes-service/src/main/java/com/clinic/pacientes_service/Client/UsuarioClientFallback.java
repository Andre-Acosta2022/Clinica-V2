package com.clinic.pacientes_service.Client;

import com.clinic.pacientes_service.DTO.UsuarioDTO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioClientFallback implements UsuarioClient {

    @Override
    public UsuarioDTO crearUsuario(String nombre, String email, String role) {
        // Retorna objeto vacío o puedes lanzar una excepción custom
        UsuarioDTO dto = new UsuarioDTO();
        dto.setNombre("Fallback usuario");
        dto.setEmail(email);
        dto.setRole(role);
        dto.setActivo(false);
        return dto;
    }

    @Override
    public UsuarioDTO getUsuarioByUsername(String username) {
        // Implementación fallback para obtener un usuario por nombre de usuario
        // Puede devolver un objeto vacío, o un objeto con valores predeterminados
        UsuarioDTO dto = new UsuarioDTO();
        dto.setNombre("Fallback usuario");
        dto.setEmail("fallback@example.com");
        dto.setRole("USER");
        dto.setActivo(false);
        return dto;
    }
}
