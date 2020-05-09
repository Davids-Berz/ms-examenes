package com.mservice.app.examenes.controllers;

import com.mservice.app.examenes.services.IExamenService;
import com.mservice.commons.examenes.models.entity.Examen;
import com.mservice.commons.examenes.models.entity.Pregunta;
import com.mservice.generic.controllers.GenericController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ExamenController extends GenericController<Examen, IExamenService> {

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Examen examen, BindingResult result, @PathVariable Long id){

        if(result.hasErrors()){
            return this.validar(result);
        }

        Optional<Examen> dbExamen = service.findById(id);

        if (dbExamen.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Examen editExamen = dbExamen.get();

        editExamen.setNombre(examen.getNombre());

        List<Pregunta> eliminadas = editExamen.getLstPreguntas()
                .stream()
                .filter(dbPregunta -> !examen.getLstPreguntas().contains(dbPregunta))
                .collect(Collectors.toList());


        eliminadas.forEach(editExamen::removePregunta);

        editExamen.setLstPreguntas(examen.getLstPreguntas());

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(editExamen));

    }

    @GetMapping("/filtrar/{text}")
    public ResponseEntity<?> findByNombre(@PathVariable String text){
        return ResponseEntity.ok().body(service.findByNombre(text));
    }

    @GetMapping("/asignaturas")
    public ResponseEntity<?> listarAsignaturas(){
        return ResponseEntity.ok().body(service.findAllAsignaturas());
    }
}



