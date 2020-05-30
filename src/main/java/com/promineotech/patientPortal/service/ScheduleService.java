package com.promineotech.patientPortal.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.patientPortal.entity.Appointment;
import com.promineotech.patientPortal.entity.Schedule;
import com.promineotech.patientPortal.entity.User;
import com.promineotech.patientPortal.repository.AppointmentRepository;
import com.promineotech.patientPortal.repository.ScheduleRepository;
import com.promineotech.patientPortal.repository.UserRepository;


@Service
public class ScheduleService {

	@Autowired
	private ScheduleRepository repo;
	
	@Autowired
	private AppointmentRepository appointmentRepo;

	@Autowired
	private UserRepository userRepo;

	public Schedule scheduleAppointment(Schedule schedule, Long userId, Long appointmentId) throws Exception {
		User user = userRepo.findOne(userId);
		Appointment appointment = appointmentRepo.findOne(appointmentId);
		if (user == null || appointment == null) {
			throw new Exception("User or appointment does not exist.");
		}
		schedule.setDate(new Date());
		schedule.setUser(user);
		schedule.setAppointment(appointment);
		return repo.save(schedule);
	}

	public void cancelAppointment(Long scheduleId) {
		repo.delete(scheduleId);
	}

}
