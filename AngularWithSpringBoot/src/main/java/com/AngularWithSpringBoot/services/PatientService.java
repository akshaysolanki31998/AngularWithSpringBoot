package com.AngularWithSpringBoot.services;

import com.AngularWithSpringBoot.Entity.Patient;
import com.AngularWithSpringBoot.repository.PatientRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private final PatientRepository repo;

    public PatientService(PatientRepository repo) {
        this.repo = repo;
    }

    public List<Patient> getAllPatients() {
        return repo.findAll();
    }

    public Optional<Patient> getPatientById(Long id) {
        return repo.findById(id);
    }

    public Patient savePatient(Patient patient) {
        return repo.save(patient);
    }

    public Patient updatePatient(Long id, Patient patient) {
        return repo.findById(id).map(existing -> {
            existing.setName(patient.getName());
            existing.setAge(patient.getAge());
            existing.setGender(patient.getGender());
            existing.setContact(patient.getContact());
            existing.setHistory(patient.getHistory());
            return repo.save(existing);
        }).orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    public void deletePatient(Long id) {
        repo.deleteById(id);
    }
}
