package com.clinic.usuarios_service.model;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class Pacientes {
    private Long usuarioId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seguro_medico_id")
    private SeguroMedico seguroMedico;
}
