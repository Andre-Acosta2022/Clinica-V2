package com.clinic.usuarios_service.repository;

import com.clinic.usuarios_service.entity.usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<usuarios,Long> {

}
