package com.AngularWithSpringBoot.Entity;



import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="doctors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String specialization;
  private String contact;
  private String availability;
  public Long getId() {
	return id;
  }
  public void setId(Long id) {
	this.id = id;
  }
  public String getName() {
	return name;
  }
  public void setName(String name) {
	this.name = name;
  }
  public String getSpecialization() {
	return specialization;
  }
  public void setSpecialization(String specialization) {
	this.specialization = specialization;
  }
  public String getContact() {
	return contact;
  }
  public void setContact(String contact) {
	this.contact = contact;
  }
  public String getAvailability() {
	return availability;
  }
  public void setAvailability(String availability) {
	this.availability = availability;
  }
  
}

