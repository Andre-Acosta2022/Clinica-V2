package com.clinic.usuarios_service.Client;

import com.clinic.usuarios_service.model.Medico;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "doctor-service", url ="https://localhost:8083")
@RequestMapping("/medicos")
public interface MedicoClient {

    @PostMapping
    public Medico save(@RequestBody Medico medico);

    @GetMapping("/usuario/{usuario_id}")
    public List<Medico> getMedico(@PathVariable("usuario_id") Long usuario_id);
}
