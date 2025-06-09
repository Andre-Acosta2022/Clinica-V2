package com.clinic.pacientes_service.Client;

import com.clinic.pacientes_service.entity.Pacientes;
import com.clinic.pacientes_service.model.usuarios;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "usuarios-service", url ="https://localhost:8081")
@RequestMapping("/usuarios")
public interface UsuarioClient {
    @PostMapping
    public Pacientes save(@RequestBody Pacientes paciente);


    @GetMapping("/usuarios/{usuario_id}")
    usuarios getUsuarioById(@PathVariable("usuario_id") Long usuarioId);
}