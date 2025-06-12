package com.clinic.pacientes_service.service;

import com.clinic.pacientes_service.Client.UsuarioClient;
import com.clinic.pacientes_service.DTO.UsuarioDTO;
import com.clinic.pacientes_service.entity.Pacientes;
import com.clinic.pacientes_service.entity.SeguroMedico;
import com.clinic.pacientes_service.repository.SeguroMedicoRepository;
import com.clinic.pacientes_service.repository.pacientesrepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PacientesService {


    private final pacientesrepository repository;

    private final SeguroMedicoRepository seguroMedicoRepository;

    private final UsuarioClient usuarioClient;

    @Autowired
    public PacientesService(pacientesrepository repository, SeguroMedicoRepository seguroMedicoRepository, UsuarioClient usuarioClient) {
        this.repository = repository;
        this.seguroMedicoRepository = seguroMedicoRepository;
        this.usuarioClient = usuarioClient;
    }
    @Transactional

    @CircuitBreaker(name = "usuariosService", fallbackMethod = "crearUsuarioFallback")
    public Pacientes crearPacienteConUsuario(Pacientes paciente, String nombre, String email) {
        // Crear usuario en el servicio de usuarios
        UsuarioDTO usuarioDTO = usuarioClient.crearUsuario(nombre, email, "PACIENTE");

        // Verificar si el usuario fue creado correctamente
        if (usuarioDTO != null && usuarioDTO.getId() != null) {
            // Asignar el usuario creado al paciente
            paciente.setUsuarioId(usuarioDTO.getId());

            // Guardar el paciente con el usuario asociado
            Pacientes pacienteGuardado = repository.save(paciente);
            return pacienteGuardado;
        } else {
            throw new RuntimeException("Error al crear el usuario para el paciente.");
        }
    }


    //Fallback en caso de que el servicio de usuarios falle
    public UsuarioDTO crearUsuarioFallback(String nombre, String email, Throwable t) {
        // Log error, retornar valor por defecto o excepción personalizada
        UsuarioDTO dto = new UsuarioDTO();
        dto.setNombre("Fallback usuario");
        dto.setEmail(email);
        dto.setRole("PACIENTE");
        dto.setActivo(false);
        return dto;  // Retorna un objeto de usuario predeterminado en caso de error
    }

    public List<Pacientes> getAll(){
        return repository.findAll();
    }
    public Pacientes getPacientesById(Long id){
        return repository.findById(id).orElse(null);
    }
    public Pacientes save(Pacientes paciente){
        Pacientes nuevopaciente =repository.save(paciente);
        return nuevopaciente;
    }
    public void delete(Long id) {
        repository.deleteById(id);
    }


    public Pacientes update(Long id, Pacientes pacienteDetalles) {
        Pacientes pacienteExistente = repository.findById(id).orElse(null);

        if (pacienteExistente != null) {
            pacienteExistente.setUsuarioId(pacienteDetalles.getUsuarioId());

            if (pacienteDetalles.getSeguroMedico() != null) {
                SeguroMedico seguroExistente = seguroMedicoRepository.findById(pacienteDetalles.getSeguroMedico().getSeguro_medico_id()).orElse(null);
                if (seguroExistente != null) {
                    pacienteExistente.setSeguroMedico(seguroExistente);
                }
            }

            if (pacienteDetalles.getDeleted() != null) {
                pacienteExistente.setDeleted(pacienteDetalles.getDeleted());
            }

            return repository.save(pacienteExistente);
        }
        return null;
    }
}

