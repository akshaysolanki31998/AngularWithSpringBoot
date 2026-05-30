package com.AngularWithSpringBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AngularWithSpringBoot.Entity.Appointment;


public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

}
