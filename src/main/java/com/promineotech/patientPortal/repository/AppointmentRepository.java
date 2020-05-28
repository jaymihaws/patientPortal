package com.promineotech.patientPortal.repository;

import org.springframework.data.repository.CrudRepository;

import com.promineotech.patientPortal.entity.Appointment;


public interface AppointmentRepository extends CrudRepository<Appointment, Long>{


}
