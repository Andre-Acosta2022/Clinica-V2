package com.clinic.usuarios_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@Table(name="direcciones")
@EntityListeners(AuditingEntityListener.class)
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // usa nombre en minúscula para evitar confusión

    @Column(nullable = false, length = 100)
    private String calle;

    @Column(nullable = false, length = 10)
    private String numero;

    @Column(length = 5)
    private String piso;

    @Column(length = 5)
    private String departamento;

    @Column(length = 50)
    private String ciudad;

    @Column(length = 50)
    private String provincia;

    @Column(length = 10)
    private String codigopostal;

    @Column(nullable = false)
    private boolean deleted = false;

}
