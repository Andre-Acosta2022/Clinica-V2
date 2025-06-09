package com.clinic.usuarios_service.Client;

import com.clinic.usuarios_service.model.Pacientes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "pacientes-service", url ="https://localhost:8082")
@RequestMapping("/pacientes")
public interface PacientesClient {

    @PostMapping
    public Pacientes save(@RequestBody Pacientes paciente);

    @GetMapping("/usuario/{usuario_id}")
    public List<Pacientes> getPacientes(@PathVariable("usuario_id") Long usuario_id);
}
