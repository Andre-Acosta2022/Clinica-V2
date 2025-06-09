package com.clinic.doctor_service.repository;


import com.clinic.doctor_service.domain.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Medico readById(Long id);
    List<Medico> findByUsuarioId(Long usuarioId);
}
