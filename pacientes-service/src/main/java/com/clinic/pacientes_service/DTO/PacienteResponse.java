package com.clinic.pacientes_service.DTO;

import com.clinic.pacientes_service.entity.Pacientes;
import com.clinic.pacientes_service.model.usuarios;
import lombok.Data;

@Data  // Lombok para getters, setters, toString, etc.
public class PacienteResponse {
    private Pacientes paciente;
    private usuarios usuario;
}