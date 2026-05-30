package com.AngularWithSpringBoot.services;

import com.AngularWithSpringBoot.DTO.PrescriptionRequest;
import com.AngularWithSpringBoot.Entity.*;
import com.AngularWithSpringBoot.repository.AppointmentRepository;
import com.AngularWithSpringBoot.repository.DoctorRepository;
import com.AngularWithSpringBoot.repository.PatientRepository;
import com.AngularWithSpringBoot.repository.PrescriptionRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepo;
    private final AppointmentRepository appointmentRepo;
    private final DoctorRepository doctorRepo;
    private final PatientRepository patientRepo;

    public PrescriptionService(PrescriptionRepository prescriptionRepo,
                               AppointmentRepository appointmentRepo,
                               DoctorRepository doctorRepo,
                               PatientRepository patientRepo) {
        this.prescriptionRepo = prescriptionRepo;
        this.appointmentRepo = appointmentRepo;
        this.doctorRepo = doctorRepo;
        this.patientRepo = patientRepo;
    }

    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepo.findAll();
    }

    public Optional<Prescription> getPrescriptionById(Long id) {
        return prescriptionRepo.findById(id);
    }

    public Prescription createPrescription(PrescriptionRequest request) {
        Appointment appointment = appointmentRepo.findById(request.getAppointmentId())
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        Doctor doctor = doctorRepo.findById(request.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        Patient patient = patientRepo.findById(request.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Prescription p = new Prescription();
        p.setAppointment(appointment);
        p.setDoctor(doctor);
        p.setPatient(patient);
        p.setDate(LocalDate.now());
        p.setNotes(request.getNotes());

        return prescriptionRepo.save(p);
    }

    public Prescription updatePrescription(Long id, PrescriptionRequest request) {
        return prescriptionRepo.findById(id).map(existing -> {
            Appointment appointment = appointmentRepo.findById(request.getAppointmentId())
                    .orElseThrow(() -> new RuntimeException("Appointment not found"));
            Doctor doctor = doctorRepo.findById(request.getDoctorId())
                    .orElseThrow(() -> new RuntimeException("Doctor not found"));
            Patient patient = patientRepo.findById(request.getPatientId())
                    .orElseThrow(() -> new RuntimeException("Patient not found"));

            existing.setAppointment(appointment);
            existing.setDoctor(doctor);
            existing.setPatient(patient);
            existing.setNotes(request.getNotes());
            existing.setDate(LocalDate.now());
            return prescriptionRepo.save(existing);
        }).orElseThrow(() -> new RuntimeException("Prescription not found"));
    }

    public void deletePrescription(Long id) {
        prescriptionRepo.deleteById(id);
    }
}
