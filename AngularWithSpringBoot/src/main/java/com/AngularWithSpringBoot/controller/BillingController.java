package com.AngularWithSpringBoot.controller;

import com.AngularWithSpringBoot.DTO.BillRequest;
import com.AngularWithSpringBoot.Entity.Bill;
import com.AngularWithSpringBoot.services.BillingService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
public class BillingController {

    private final BillingService service;

    public BillingController(BillingService service) {
        this.service = service;
    }

    // GET all bills
    @GetMapping
    public List<Bill> getAll() {
        return service.getAllBills();
    }

    // GET bill by ID
    @GetMapping("/{id}")
    public ResponseEntity<Bill> getById(@PathVariable Long id) {
        return service.getBillById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CREATE bill
    @PostMapping
    public Bill create(@RequestBody BillRequest request) {
        return service.createBill(request);
    }

    // UPDATE bill
    @PutMapping("/{id}")
    public ResponseEntity<Bill> update(@PathVariable Long id, @RequestBody BillRequest request) {
        try {
            return ResponseEntity.ok(service.updateBill(id, request));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE bill
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteBill(id);
        return ResponseEntity.noContent().build();
    }
}
