package com.promineotech.patientPortal.repository;

import org.springframework.data.repository.CrudRepository;

import com.promineotech.patientPortal.entity.Schedule;

public interface ScheduleRepository extends CrudRepository<Schedule, Long> {

}
