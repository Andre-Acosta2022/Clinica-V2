package com.clinic.usuarios_service.repository;

import com.clinic.usuarios_service.entity.usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<usuarios,Long> {
    Optional<usuarios> findByEmail(String email);
    Optional<usuarios> findByTokenActivacion(String token);
    Optional<usuarios> findByDni(String dni);  // Agregado método findByDni

}
