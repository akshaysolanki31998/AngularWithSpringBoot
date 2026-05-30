package com.AngularWithSpringBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AngularWithSpringBoot.Entity.Prescription;

public interface PrescriptionRepository  extends JpaRepository<Prescription, Long>{

}
