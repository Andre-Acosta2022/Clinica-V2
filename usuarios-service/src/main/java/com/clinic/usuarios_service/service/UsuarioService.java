package com.clinic.usuarios_service.service;


import com.clinic.usuarios_service.Client.MedicoClient;
import com.clinic.usuarios_service.Client.PacientesClient;
import com.clinic.usuarios_service.entity.usuarios;
import com.clinic.usuarios_service.model.Medico;
import com.clinic.usuarios_service.model.Pacientes;
import com.clinic.usuarios_service.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private PacientesClient pacientesClient;
    @Autowired
    private MedicoClient medicoClient;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<usuarios> getAll(){
        return repository.findAll();
    }
    public usuarios getUsuarioById(Long usuario_id) {
        // Hacer una solicitud GET al microservicio de usuarios
        return restTemplate.getForObject("http://localhost:8081/usuarios/" + usuario_id, usuarios.class);
    }
    public usuarios save(usuarios usuario){
        usuarios nuevousuario =repository.save(usuario);
        return nuevousuario;
    }
    public void delete(Long id) {
        repository.deleteById(id);
    }
    public usuarios update(Long id, usuarios usuarioDetalles) {
        usuarios usuarioExistente = repository.findById(id).orElse(null);
        if (usuarioExistente != null) {
            usuarioExistente.setNombre(usuarioDetalles.getNombre());
            usuarioExistente.setApellido(usuarioDetalles.getApellido());
            usuarioExistente.setDni(usuarioDetalles.getDni());
            usuarioExistente.setFecha_nacimiento(usuarioDetalles.getFecha_nacimiento());
            usuarioExistente.setEmail(usuarioDetalles.getEmail());
            usuarioExistente.setTelefono(usuarioDetalles.getTelefono());
            usuarioExistente.setGenero(usuarioDetalles.getGenero());
            usuarioExistente.setDireccion(usuarioDetalles.getDireccion());
            return repository.save(usuarioExistente);
        }
        return null;
    }


    public List<Pacientes> getPaciente(Long usuario_id) {
        List<Pacientes> pacientes = restTemplate.getForObject("http://localhost:8082/paciente/usuario" + usuario_id, List.class);
        return pacientes;
    }
    public List<Medico> getMedico(Long usuario_id){
        List<Medico> medicos= restTemplate.getForObject("http://localhost:8083/medico/usuario"+usuario_id,List.class);
        return medicos;
    }
    public Pacientes savePacientes(Long usuario_id,Pacientes paciente){
        paciente.setUsuarioId(usuario_id);
        Pacientes nuevopaciente =pacientesClient.save(paciente);
        return paciente;
    }
    public Medico saveMedico(Long usuario_id, Medico medico){
        medico.setUsuarioId(usuario_id);
        Medico nuevoMedico =medicoClient.save(medico);
        return medico;
    }
}
