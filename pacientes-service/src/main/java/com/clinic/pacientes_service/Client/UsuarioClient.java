package com.clinic.pacientes_service.Client;

import com.clinic.pacientes_service.DTO.UsuarioDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuarios-service", fallback = UsuarioClientFallback.class)
public interface UsuarioClient {
    @PostMapping("/api/usuarios/crear")
    UsuarioDTO crearUsuario(@RequestParam String nombre,
                            @RequestParam String email,
                            @RequestParam String role);
    @GetMapping("/usuarios/{username}")
    UsuarioDTO getUsuarioByUsername(@PathVariable("username") String username);
}