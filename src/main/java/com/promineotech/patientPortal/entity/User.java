package com.promineotech.patientPortal.entity;

import java.util.Set;


import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class User {

	private Long id;
	private String username;
	private String hash;
	private String role;
	
	@JsonIgnore
	private Set<Appointment> appointments;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@Column(unique=true)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonIgnore
	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	@OneToMany
	@JoinColumn(name = "user")
	public Set<Appointment> getAppointment() {
		return appointments;
	}

	public void setAppointment(Set<Appointment> appointments) {
		this.appointments = appointments;
	}


}
