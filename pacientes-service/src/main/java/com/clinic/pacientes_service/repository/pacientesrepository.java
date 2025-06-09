package com.clinic.pacientes_service.repository;

import com.clinic.pacientes_service.entity.Pacientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface pacientesrepository  extends JpaRepository<Pacientes,Long> {

    Pacientes findByUsuarioId(Long usuario_id);
}
