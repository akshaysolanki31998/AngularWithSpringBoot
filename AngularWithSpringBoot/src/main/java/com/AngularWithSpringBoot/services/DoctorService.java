package com.AngularWithSpringBoot.services;


import org.springframework.stereotype.Service;

import com.AngularWithSpringBoot.Entity.Doctor;
import com.AngularWithSpringBoot.repository.DoctorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
  private final DoctorRepository repo;
  public DoctorService(DoctorRepository repo){ this.repo = repo; }

  public List<Doctor> getAll(){ return repo.findAll(); }
  public Optional<Doctor> getById(Long id){ return repo.findById(id); }
  public Doctor create(Doctor d){ return repo.save(d); }
  public Doctor update(Long id, Doctor d){
    return repo.findById(id).map(existing -> {
      existing.setName(d.getName());
      existing.setSpecialization(d.getSpecialization());
      existing.setContact(d.getContact());
      existing.setAvailability(d.getAvailability());
      return repo.save(existing);
    }).orElseThrow(() -> new RuntimeException("Doctor not found"));
  }
  public void delete(Long id){ repo.deleteById(id); }
}
