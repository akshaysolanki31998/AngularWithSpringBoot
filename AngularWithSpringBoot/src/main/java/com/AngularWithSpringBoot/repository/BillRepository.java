package com.AngularWithSpringBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AngularWithSpringBoot.Entity.Bill;

public interface BillRepository extends JpaRepository<Bill, Long> {

}
