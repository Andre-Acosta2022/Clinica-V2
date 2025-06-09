package com.clinic.pacientes_service.repository;

import com.clinic.pacientes_service.entity.SeguroMedico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeguroMedicoRepository extends JpaRepository<SeguroMedico,Long> {
}
