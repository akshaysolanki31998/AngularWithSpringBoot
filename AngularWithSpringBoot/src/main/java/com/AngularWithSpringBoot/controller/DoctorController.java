package com.AngularWithSpringBoot.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.AngularWithSpringBoot.Entity.Doctor;
import com.AngularWithSpringBoot.services.DoctorService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/doctors")
public class DoctorController {
  private final DoctorService service;
  public DoctorController(DoctorService service){ this.service = service; }

  @GetMapping
  public List<Doctor> getAll(){ return service.getAll(); }

  @GetMapping("/{id}")
  public ResponseEntity<Doctor> getById(@PathVariable Long id){
    return service.getById(id).map(ResponseEntity::ok)
           .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Doctor> create(@RequestBody Doctor d){
    Doctor saved = service.create(d);
    return ResponseEntity.status(201).body(saved);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Doctor> update(@PathVariable Long id, @RequestBody Doctor d){
    try {
      Doctor updated = service.update(id, d);
      return ResponseEntity.ok(updated);
    } catch (RuntimeException ex){
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id){
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
