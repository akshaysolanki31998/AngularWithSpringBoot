package com.AngularWithSpringBoot.controller;

import com.AngularWithSpringBoot.Entity.Patient;
import com.AngularWithSpringBoot.services.PatientService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    // GET all patients
    @GetMapping
    public List<Patient> getAll() {
        return service.getAllPatients();
    }

    // GET patient by ID
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getById(@PathVariable Long id) {
        return service.getPatientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CREATE patient
    @PostMapping
    public Patient create(@RequestBody Patient patient) {
        return service.savePatient(patient);
    }

    // UPDATE patient
    @PutMapping("/{id}")
    public ResponseEntity<Patient> update(@PathVariable Long id, @RequestBody Patient patient) {
        try {
            return ResponseEntity.ok(service.updatePatient(id, patient));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE patient
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
