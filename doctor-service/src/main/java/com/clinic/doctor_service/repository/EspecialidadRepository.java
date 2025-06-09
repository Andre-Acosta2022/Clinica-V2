package com.clinic.doctor_service.repository;

import com.clinic.doctor_service.domain.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {
    Especialidad readById(Long id);
}
