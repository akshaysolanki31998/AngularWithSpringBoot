package com.AngularWithSpringBoot.services;

import com.AngularWithSpringBoot.DTO.BillRequest;
import com.AngularWithSpringBoot.Entity.Bill;
import com.AngularWithSpringBoot.Entity.Patient;
import com.AngularWithSpringBoot.repository.BillRepository;
import com.AngularWithSpringBoot.repository.PatientRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BillingService {

    private final BillRepository billRepo;
    private final PatientRepository patientRepo;

    public BillingService(BillRepository billRepo, PatientRepository patientRepo) {
        this.billRepo = billRepo;
        this.patientRepo = patientRepo;
    }

    public List<Bill> getAllBills() {
        return billRepo.findAll();
    }

    public Optional<Bill> getBillById(Long id) {
        return billRepo.findById(id);
    }

    public Bill createBill(BillRequest request) {
        Patient patient = patientRepo.findById(request.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Bill bill = new Bill();
        bill.setPatient(patient);
        bill.setAmount(request.getAmount());
        bill.setBillDate(LocalDate.now());
        bill.setStatus(request.getStatus() != null ? request.getStatus() : "UNPAID");

        return billRepo.save(bill);
    }

    public Bill updateBill(Long id, BillRequest request) {
        return billRepo.findById(id).map(existing -> {
            Patient patient = patientRepo.findById(request.getPatientId())
                    .orElseThrow(() -> new RuntimeException("Patient not found"));

            existing.setPatient(patient);
            existing.setAmount(request.getAmount());
            existing.setStatus(request.getStatus());
            existing.setBillDate(LocalDate.now());
            return billRepo.save(existing);
        }).orElseThrow(() -> new RuntimeException("Bill not found"));
    }

    public void deleteBill(Long id) {
        billRepo.deleteById(id);
    }
}
