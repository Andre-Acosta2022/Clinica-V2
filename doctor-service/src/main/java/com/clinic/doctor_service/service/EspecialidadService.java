package com.clinic.doctor_service.service;



import com.clinic.doctor_service.domain.Especialidad;

import java.util.List;

public interface EspecialidadService {
    Especialidad create(Especialidad e);
    Especialidad update(Especialidad e);
    void delete(Especialidad id);
    Especialidad read(Long id);
    List<Especialidad> readAll();

}
