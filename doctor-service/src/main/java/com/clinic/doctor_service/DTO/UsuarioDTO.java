package com.clinic.doctor_service.DTO;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private boolean activo;
}
