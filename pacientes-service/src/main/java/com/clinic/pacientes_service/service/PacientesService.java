package com.clinic.pacientes_service.service;

import com.clinic.pacientes_service.Client.UsuarioClient;
import com.clinic.pacientes_service.DTO.PacienteResponse;
import com.clinic.pacientes_service.entity.Pacientes;
import com.clinic.pacientes_service.entity.SeguroMedico;
import com.clinic.pacientes_service.model.usuarios;
import com.clinic.pacientes_service.repository.SeguroMedicoRepository;
import com.clinic.pacientes_service.repository.pacientesrepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PacientesService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired

    private pacientesrepository repository;
    @Autowired
    private SeguroMedicoRepository seguroMedicoRepository;
    @Autowired
    private UsuarioClient usuarioClient;


    public PacienteResponse obtenerPacienteConDatos(Long usuario_id) {
        // Obtener usuario usando FeignClient (simplificado)
        usuarios usuario = usuarioClient.getUsuarioById(usuario_id);

        // Obtener paciente usando RestTemplate (más control sobre la solicitud)
        String url = "http://localhost:8082/pacientes/usuario/" + usuario_id;
        Pacientes paciente = restTemplate.getForObject(url, Pacientes.class);

        // Crear y devolver la respuesta combinada
        PacienteResponse respuesta = new PacienteResponse();
        respuesta.setPaciente(paciente);
        respuesta.setUsuario(usuario);
        return respuesta;
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
        // Buscamos el paciente existente por ID
        Pacientes pacienteExistente = repository.findById(id).orElse(null);

        if (pacienteExistente != null) {
            // Actualizamos los campos con los valores nuevos de 'pacienteDetalles'
            pacienteExistente.setUsuarioId(pacienteDetalles.getUsuarioId());

            // Si el seguroMedico viene con ID, cargamos ese seguroMedico
            if (pacienteDetalles.getSeguroMedico() != null) {
                SeguroMedico seguroExistente = seguroMedicoRepository.findById(pacienteDetalles.getSeguroMedico().getSeguro_medico_id()).orElse(null);
                if (seguroExistente != null) {
                    pacienteExistente.setSeguroMedico(seguroExistente); // Asignamos el seguro médico actualizado
                }
            }

            // Actualizamos el valor de 'deleted' si es necesario
            if (pacienteDetalles.getDeleted() != null) {
                pacienteExistente.setDeleted(pacienteDetalles.getDeleted());
            }

            // Guardamos el paciente actualizado
            return repository.save(pacienteExistente);
        }
        return null; // Si no se encontró el paciente, retornamos null
    }
}
