package com.AngularWithSpringBoot.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="demo")
public class Student {
	  @Id 
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private  int rolleno;
	private  String email;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRolleno() {
		return rolleno;
	}
	public void setRolleno(int rolleno) {
		this.rolleno = rolleno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
