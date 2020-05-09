package com.mservice.app.examenes.services;

import com.mservice.commons.examenes.models.entity.Asignatura;
import com.mservice.commons.examenes.models.entity.Examen;
import com.mservice.generic.services.IGenericService;

import java.util.List;

public interface IExamenService  extends IGenericService<Examen> {

    List<Examen> findByNombre(String Text);
    Iterable<Asignatura> findAllAsignaturas();

}
