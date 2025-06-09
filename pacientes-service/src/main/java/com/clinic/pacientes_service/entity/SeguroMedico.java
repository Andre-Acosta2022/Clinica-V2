package com.clinic.pacientes_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="seguro_medico")
public class SeguroMedico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seguro_medico_id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean deleted = false;
}