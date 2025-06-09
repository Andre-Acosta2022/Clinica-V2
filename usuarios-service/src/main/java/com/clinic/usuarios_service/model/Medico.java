package com.clinic.usuarios_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
@Data
public class Medico {
    private Long id;
    private Long usuarioId;

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Turno> turnos;

    @ManyToOne
    @JoinColumn(name ="especialidad_id", nullable = false)
    private Especialidad especialidad;
}
