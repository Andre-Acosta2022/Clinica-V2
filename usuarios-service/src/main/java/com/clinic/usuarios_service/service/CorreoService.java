package com.clinic.usuarios_service.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CorreoService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void enviarCorreoActivacion(String email, String token) {
        // Se envía el correo de forma asíncrona usando RabbitMQ o Kafka
        String mensaje = "Por favor, active su cuenta con el siguiente enlace: http://frontend-clinica/activar?token=" + token;
        rabbitTemplate.convertAndSend("emailQueue", mensaje);
    }
}
