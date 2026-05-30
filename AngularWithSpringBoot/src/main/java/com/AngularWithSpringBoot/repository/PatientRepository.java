package com.AngularWithSpringBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AngularWithSpringBoot.Entity.Patient;

public interface PatientRepository extends JpaRepository<Patient,Long> {

}
