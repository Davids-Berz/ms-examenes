package com.mservice.app.examenes.models.repository;

import com.mservice.commons.examenes.models.entity.Asignatura;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IAsignaturaRepository extends PagingAndSortingRepository<Asignatura, Long> {

}
