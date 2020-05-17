package com.promineotech.patientPortal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCrypt;

@ComponentScan("com.promineotech.patientPortal")
@SpringBootApplication
public class App
{
	public static void main(String[] args) 
	{
		SpringApplication.run(App.class, args);
		
	}
	
}
