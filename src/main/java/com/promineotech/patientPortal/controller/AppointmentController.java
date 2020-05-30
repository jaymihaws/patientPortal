package com.promineotech.patientPortal.controller;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.patientPortal.entity.Appointment;
import com.promineotech.patientPortal.service.AppointmentService;


@RestController
@RequestMapping("/users/{userId}/appointments")
public class AppointmentController {

	
	@Autowired
	private AppointmentService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> getAllAppointments() {
		return new ResponseEntity<Object>(service.getAllAppointments(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{appointmentId}", method = RequestMethod.GET)
	public ResponseEntity<Object> getAppointment(@PathVariable Long appointmentId) {
		return new ResponseEntity<Object>(service.getAppointment(appointmentId), HttpStatus.OK);
	}

	@RequestMapping(value = "/{appointmentId}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateAppointment(@RequestBody Appointment appointment, @PathVariable Long appointmentId) {
		try {
			return new ResponseEntity<Object>(service.updateAppointment(appointment, appointmentId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> createAppointment(@RequestBody Appointment appointment, @PathVariable Long userId
			, HttpServletRequest request) {
		try {
			return new ResponseEntity<Object>(service.createAppointment(appointment, userId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
