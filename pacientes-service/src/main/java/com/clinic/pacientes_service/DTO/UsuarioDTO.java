package com.clinic.pacientes_service.DTO;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String email;
    private String role;
    private boolean activo;
}
