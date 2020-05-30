package com.promineotech.patientPortal.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.patientPortal.entity.Appointment;
import com.promineotech.patientPortal.entity.User;
import com.promineotech.patientPortal.repository.AppointmentRepository;
import com.promineotech.patientPortal.repository.UserRepository;


@Service
public class AppointmentService {
	
	@Autowired
	private AppointmentRepository repo;

	@Autowired
	private UserRepository userRepo;


	public Iterable<Appointment> getAllAppointments() {
		return repo.findAll();
	}

	public Appointment getAppointment(Long id) {
		return repo.findOne(id);
	}

	public Appointment updateAppointment(Appointment appointment, Long id) throws Exception {
		Appointment foundAppointment = repo.findOne(id);
		if (foundAppointment == null) {
			throw new Exception("Appointment not found.");
		}
		foundAppointment.setContent(appointment.getContent());
		return repo.save(foundAppointment);
	}
	
	public Appointment createAppointment(Appointment appointment, Long userId) throws Exception {
		User user = userRepo.findOne(userId);
		if (user == null) {
			throw new Exception("User not found.");
		}
		appointment.setDate(new Date());
		appointment.setUser(user);
		return repo.save(appointment);
	}

}
