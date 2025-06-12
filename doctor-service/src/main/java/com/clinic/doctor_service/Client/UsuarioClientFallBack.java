package com.clinic.doctor_service.Client;

import com.clinic.doctor_service.DTO.UsuarioDTO;
import org.springframework.stereotype.Component;
@Component
public class UsuarioClientFallBack {
    @Component
    public class UsuarioClientFallback implements UsuarioClient {

        @Override
        public UsuarioDTO crearUsuario(String nombre, String email, String role) {
            // Retorna objeto vacío o puedes lanzar una excepción custom
            UsuarioDTO dto = new UsuarioDTO();
            dto.setNombre("Fallback usuario");
            dto.setApellido("Fallback usuario");
            dto.setActivo(false);
            return dto;
        }

        @Override
        public UsuarioDTO getUsuarioByUsername(String username) {
            // Implementación fallback para obtener un usuario por nombre de usuario
            // Puede devolver un objeto vacío, o un objeto con valores predeterminados
            UsuarioDTO dto = new UsuarioDTO();
            dto.setNombre("Fallback usuario");
            dto.setApellido("Fallback usuario");
            dto.setActivo(false);
            return dto;
        }
    }

}
