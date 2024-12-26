package com.tresit.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tresit.student.model.Career;
import com.tresit.student.service.CareerService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/carreras")
public class CareerController {

    @Autowired
    CareerService service;

    @GetMapping
    public ResponseEntity<List<Career>> getAllCareers() {
        List<Career> careers = service.getAllCareers();
        if (careers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(careers);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Career> getCareerById(@PathVariable Long id) {
        Career career = service.getCareerById(id);
        return ResponseEntity.ok(career);
    }

    @PostMapping("/agregar")
    public ResponseEntity<Career> addCareer(@RequestBody Career career) {
        Career createdCareer = service.addCareer(career);
        return ResponseEntity.status(201).body(createdCareer);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteCareer(@PathVariable Long id) {
        service.deleteCareer(id);
        return ResponseEntity.noContent().build();
    }

}
