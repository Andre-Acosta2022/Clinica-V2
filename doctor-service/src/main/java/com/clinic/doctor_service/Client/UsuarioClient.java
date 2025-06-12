package com.clinic.doctor_service.Client;

import com.clinic.doctor_service.DTO.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "usuarios-service", fallback = UsuarioClientFallBack.class)
public interface UsuarioClient {
    @PostMapping("/api/usuarios/crear")
    UsuarioDTO crearUsuario(@RequestParam String nombre,
                            @RequestParam String email,
                            @RequestParam String role);
    @GetMapping("/usuarios/{username}")
    UsuarioDTO getUsuarioByUsername(@PathVariable("username") String username);
}
