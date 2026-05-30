package com.AngularWithSpringBoot.controller;

import com.AngularWithSpringBoot.DTO.AppointmentRequest;
import com.AngularWithSpringBoot.Entity.Appointment;
import com.AngularWithSpringBoot.services.AppointmentService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    // GET all
    @GetMapping
    public List<Appointment> getAll() {
        return service.getAllAppointments();
    }

    // GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getById(@PathVariable Long id) {
        return service.getAppointmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CREATE appointment
    @PostMapping
    public Appointment create(@RequestBody AppointmentRequest request) {
        return service.createAppointment(request);
    }

    // UPDATE appointment
    @PutMapping("/{id}")
    public ResponseEntity<Appointment> update(@PathVariable Long id,
                                              @RequestBody AppointmentRequest request) {
        try {
            return ResponseEntity.ok(service.updateAppointment(id, request));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE appointment
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }

    // UPDATE status only
    @PatchMapping("/{id}/status")
    public ResponseEntity<Appointment> updateStatus(@PathVariable Long id,
                                                    @RequestParam String status) {
        try {
            return ResponseEntity.ok(service.updateStatus(id, status));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
