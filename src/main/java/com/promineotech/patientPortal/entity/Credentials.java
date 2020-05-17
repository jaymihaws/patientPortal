package com.promineotech.patientPortal.entity;

import javax.persistence.Column;

public class Credentials {
	
	private String username;
	private String password;
	
	@Column(unique=true)
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
