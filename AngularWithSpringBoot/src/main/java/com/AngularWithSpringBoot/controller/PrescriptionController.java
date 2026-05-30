package com.AngularWithSpringBoot.controller;

import com.AngularWithSpringBoot.DTO.PrescriptionRequest;
import com.AngularWithSpringBoot.Entity.Prescription;
import com.AngularWithSpringBoot.services.PrescriptionService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    private final PrescriptionService service;

    public PrescriptionController(PrescriptionService service) {
        this.service = service;
    }

    // GET all prescriptions
    @GetMapping
    public List<Prescription> getAll() {
        return service.getAllPrescriptions();
    }

    // GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<Prescription> getById(@PathVariable Long id) {
        return service.getPrescriptionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CREATE prescription
    @PostMapping
    public Prescription create(@RequestBody PrescriptionRequest request) {
        return service.createPrescription(request);
    }

    // UPDATE prescription
    @PutMapping("/{id}")
    public ResponseEntity<Prescription> update(@PathVariable Long id,
                                               @RequestBody PrescriptionRequest request) {
        try {
            return ResponseEntity.ok(service.updatePrescription(id, request));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE prescription
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deletePrescription(id);
        return ResponseEntity.noContent().build();
    }
}
