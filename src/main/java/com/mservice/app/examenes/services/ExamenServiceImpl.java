package com.mservice.app.examenes.services;

import com.mservice.app.examenes.models.repository.IAsignaturaRepository;
import com.mservice.app.examenes.models.repository.IExamenRepository;
import com.mservice.commons.examenes.models.entity.Asignatura;
import com.mservice.commons.examenes.models.entity.Examen;
import com.mservice.generic.services.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExamenServiceImpl extends GenericServiceImpl<Examen, IExamenRepository> implements IExamenService {

    @Autowired
    private IAsignaturaRepository asignaturaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Examen> findByNombre(String text) {
        return repository.findByNombre(text);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Asignatura> findAllAsignaturas() {
        return asignaturaRepository.findAll();
    }
}
