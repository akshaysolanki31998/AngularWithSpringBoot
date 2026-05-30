package com.AngularWithSpringBoot.services;

import com.AngularWithSpringBoot.DTO.AppointmentRequest;
import com.AngularWithSpringBoot.Entity.Appointment;
import com.AngularWithSpringBoot.Entity.Doctor;
import com.AngularWithSpringBoot.Entity.Patient;
import com.AngularWithSpringBoot.repository.AppointmentRepository;
import com.AngularWithSpringBoot.repository.DoctorRepository;
import com.AngularWithSpringBoot.repository.PatientRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepo;
    private final PatientRepository patientRepo;
    private final DoctorRepository doctorRepo;

    public AppointmentService(AppointmentRepository appointmentRepo,
                              PatientRepository patientRepo,
                              DoctorRepository doctorRepo) {
        this.appointmentRepo = appointmentRepo;
        this.patientRepo = patientRepo;
        this.doctorRepo = doctorRepo;
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepo.findAll();
    }

    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepo.findById(id);
    }

    public Appointment createAppointment(AppointmentRequest request) {
        Patient patient = patientRepo.findById(request.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        Doctor doctor = doctorRepo.findById(request.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setStatus("BOOKED");

        return appointmentRepo.save(appointment);
    }

    public Appointment updateAppointment(Long id, AppointmentRequest request) {
        return appointmentRepo.findById(id).map(existing -> {
            Patient patient = patientRepo.findById(request.getPatientId())
                    .orElseThrow(() -> new RuntimeException("Patient not found"));
            Doctor doctor = doctorRepo.findById(request.getDoctorId())
                    .orElseThrow(() -> new RuntimeException("Doctor not found"));

            existing.setPatient(patient);
            existing.setDoctor(doctor);
            existing.setAppointmentDate(request.getAppointmentDate());
            return appointmentRepo.save(existing);
        }).orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    public void deleteAppointment(Long id) {
        appointmentRepo.deleteById(id);
    }

    public Appointment updateStatus(Long id, String status) {
        return appointmentRepo.findById(id).map(existing -> {
            existing.setStatus(status);
            return appointmentRepo.save(existing);
        }).orElseThrow(() -> new RuntimeException("Appointment not found"));
    }
}
