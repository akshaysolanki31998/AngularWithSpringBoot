package com.AngularWithSpringBoot.DTO;

import lombok.Data;

@Data
public class BillRequest {
    public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	private Long patientId;
    private Double amount;
    private String status; // optional, default = UNPAID
}
