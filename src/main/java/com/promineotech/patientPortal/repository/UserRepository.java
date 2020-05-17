package com.promineotech.patientPortal.repository;

import org.springframework.data.repository.CrudRepository;
import com.promineotech.patientPortal.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	public User findByUsername(String username);
	
	

}
