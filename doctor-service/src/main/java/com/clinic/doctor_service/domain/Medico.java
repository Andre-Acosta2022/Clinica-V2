package com.clinic.doctor_service.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "usuario_id", nullable = false, unique = true)
    private Long usuarioId;
    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Turno> turnos;

    @ManyToOne
    @JoinColumn(name ="especialidad_id", nullable = false)
    private Especialidad especialidad;
}
