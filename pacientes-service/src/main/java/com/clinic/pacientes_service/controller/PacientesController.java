package com.clinic.pacientes_service.controller;

import com.clinic.pacientes_service.DTO.PacienteResponse;
import com.clinic.pacientes_service.entity.Pacientes;
import com.clinic.pacientes_service.service.PacientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
@RestController
@RequestMapping("/api/pacientes")
public class PacientesController {
    @Autowired
    private PacientesService pacienteService;

    // Listar todos los pacientes
    @GetMapping
    public ResponseEntity<List<Pacientes>> listarPacientes() {
        List<Pacientes> pacientes = pacienteService.getAll(); // Obtiene todos los pacientes
        if (pacientes.isEmpty()) {
            return ResponseEntity.noContent().build(); // Si no hay pacientes, retorna 204
        }
        return ResponseEntity.ok(pacientes); // Retorna los pacientes
    }

    // Obtener un paciente por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Pacientes> obtenerPaciente(@PathVariable("id") Long id) {
        Pacientes paciente = pacienteService.getPacientesById(id);
        if (paciente == null) {
            return ResponseEntity.notFound().build(); // Si no se encuentra, retorna 404
        }
        return ResponseEntity.ok(paciente); // Retorna el paciente encontrado
    }
    // Guardar un nuevo paciente
    @PostMapping
    public ResponseEntity<Pacientes> guardarPaciente(@RequestBody Pacientes paciente) {
        Pacientes nuevoPaciente = pacienteService.save(paciente); // Guarda el nuevo paciente
        return ResponseEntity.ok(nuevoPaciente); // Retorna el paciente guardado
    }

    // Actualizar un paciente existente
    @PutMapping("/{id}")
    public ResponseEntity<Pacientes> editarPaciente(@PathVariable("id") Long id, @RequestBody Pacientes pacienteDetalles) {
        Pacientes pacienteActualizado = pacienteService.update(id, pacienteDetalles);
        if (pacienteActualizado == null) {
            return ResponseEntity.notFound().build(); // Si no se encuentra el paciente, retorna 404
        }
        return ResponseEntity.ok(pacienteActualizado); // Retorna el paciente actualizado
    }

    // Eliminar un paciente por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPaciente(@PathVariable("id") Long id) {
        Pacientes paciente = pacienteService.getPacientesById(id);
        if (paciente == null) {
            return ResponseEntity.notFound().build(); // Si no se encuentra el paciente, retorna 404
        }
        pacienteService.delete(id);  // Elimina el paciente
        return ResponseEntity.noContent().build(); // Retorna código 204 (sin contenido) para indicar éxito
    }

    @GetMapping("/{usuario_id}")
    public ResponseEntity<PacienteResponse> obtenerPacienteConDatos(@PathVariable("usuario_id") Long usuarioId) {
        PacienteResponse respuesta = pacienteService.obtenerPacienteConDatos(usuarioId);
        return ResponseEntity.ok(respuesta);
    }
}
