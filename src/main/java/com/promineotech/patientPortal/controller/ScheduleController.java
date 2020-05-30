package com.promineotech.patientPortal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.patientPortal.entity.Schedule;
import com.promineotech.patientPortal.service.ScheduleService;

@RestController
@RequestMapping("/users/{userId}/appointments/{appointmentId}/schedules")
public class ScheduleController {

	@Autowired
	private ScheduleService service;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> scheduleAppointment(@RequestBody Schedule schedule, @PathVariable Long userId,
			@PathVariable Long appointmentId) {
		try {
			return new ResponseEntity<Object>(service.scheduleAppointment(schedule, userId, appointmentId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/{scheduleId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> cancelAppointment(@PathVariable Long scheduleId) {
		service.cancelAppointment(scheduleId);
		return new ResponseEntity<Object>("Cancelled appointment with id:" + scheduleId, HttpStatus.OK);
	}

}
