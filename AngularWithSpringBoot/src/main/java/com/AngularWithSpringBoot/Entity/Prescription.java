package com.AngularWithSpringBoot.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name="prescriptions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prescription {
  public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Appointment getAppointment() {
		return appointment;
	}
	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name="appointment_id")
  private Appointment appointment;

  @ManyToOne
  @JoinColumn(name="doctor_id")
  private Doctor doctor;

  @ManyToOne
  @JoinColumn(name="patient_id")
  private Patient patient;

  private String filePath; // for file storage reference (optional)
  private LocalDate date;
  @Column(length=2000)
  private String notes;
}
