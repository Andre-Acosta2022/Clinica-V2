package com.clinic.pacientes_service.model;

import jakarta.persistence.Column;

public class Direccion {

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
