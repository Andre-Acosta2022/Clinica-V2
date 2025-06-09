package com.clinic.pacientes_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public class usuarios {

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String apellido;

    @Column(nullable = false, length = 8, unique = true)
    @Pattern(regexp = "\\d{8}", message = "DNI debe tener 8 dígitos")
    private String dni;

    @Column(nullable = false)
    private LocalDate fecha_nacimiento;

    @Column(nullable = false)
    @Email(message = "Email debe tener formato válido")
    private String email;

    @Column(nullable = false, length = 25)
    @Pattern(regexp = "\\d{9}", message = "Teléfono debe tener 9 dígitos")
    private String telefono;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genero genero;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id")
    private Direccion direccion;

    @Column(nullable = false)
    private boolean deleted = false;
}
