package com.clinic.usuarios_service.controller;

import com.clinic.usuarios_service.entity.usuarios;
import com.clinic.usuarios_service.model.Medico;
import com.clinic.usuarios_service.model.Pacientes;
import com.clinic.usuarios_service.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<usuarios>> listarUsuarios(){
        List<usuarios> usuario=usuarioService.getAll().reversed();
        if (usuario.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuario);
    }
    @GetMapping("/{id}")
    public ResponseEntity<usuarios> obtenerUsuarios(@PathVariable("id")Long id){
        usuarios usuario= usuarioService.getUsuarioById(id);
        if (usuario == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }
    @PostMapping
    public ResponseEntity<usuarios> guardarUsuario(@RequestBody usuarios usuario) {
        usuarios nuevousuario = usuarioService.save(usuario);
        return ResponseEntity.ok(nuevousuario);
    }
    @PutMapping("/{id}")
    public ResponseEntity<usuarios> editarUsuario(@PathVariable("id") Long id, @RequestBody usuarios usuarioDetalles) {
        usuarios usuarioActualizado = usuarioService.update(id, usuarioDetalles);
        if (usuarioActualizado == null) {
            return ResponseEntity.notFound().build(); // Si el usuario no existe, retorna 404
        }
        return ResponseEntity.ok(usuarioActualizado); // Retorna el usuario actualizado
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable("id") Long id) {
        usuarios usuario = usuarioService.getUsuarioById(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build(); // Usuario no encontrado
        }
        usuarioService.delete(id);  // Eliminar usuario
        return ResponseEntity.noContent().build(); // Retorna código 204 (sin contenido) para indicar éxito
    }

    /*---------------------------------*/
    @GetMapping("/pacientes/{usuario_id}")
    public ResponseEntity<List<Pacientes>> listarPacientes(@PathVariable("usuario_id") Long id){
        usuarios usuario = usuarioService.getUsuarioById(id);
        if(usuario == null) {
            return ResponseEntity.notFound().build();
        }

        List<Pacientes> pacientes = usuarioService.getPaciente(id);
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/Medico/{usuario_id}")
    public ResponseEntity<List<Medico>> listarMedicos(@PathVariable("usuario_id") Long id){
        usuarios usuario = usuarioService.getUsuarioById(id);
        if(usuario == null) {
            return ResponseEntity.notFound().build();
        }

        List<Medico> medicos = usuarioService.getMedico(id);
        return ResponseEntity.ok(medicos);
    }

    @PostMapping("/pacientes/{usuario_id}")
    public ResponseEntity<Pacientes> guardarPaciente(@PathVariable("usuario_id") Long usuario_id,@RequestBody Pacientes paciente){
        Pacientes nuevopaciente = usuarioService.savePacientes(usuario_id,paciente);
        return ResponseEntity.ok(nuevopaciente);
    }

    @PostMapping("/medico/{usuario_id}")
    public ResponseEntity<Medico> guardarMoto(@PathVariable("usuarioId") Long usuario_id,@RequestBody Medico medico){
        Medico nuevaMedico = usuarioService.saveMedico(usuario_id, medico);
        return ResponseEntity.ok(nuevaMedico);
    }

}
