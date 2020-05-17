package com.promineotech.patientPortal.service;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.promineotech.patientPortal.entity.Credentials;
import com.promineotech.patientPortal.entity.User;
import com.promineotech.patientPortal.repository.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User register(Credentials cred) throws AuthenticationException {
		User user = new User();
		user.setUsername(cred.getUsername());
		user.setHash(BCrypt.hashpw(cred.getPassword(), BCrypt.gensalt()));
		try {
			userRepository.save(user);
			return user;
		} catch (DataIntegrityViolationException e) {
			throw new AuthenticationException ("Username not available.");
		}
	}

	public User login(Credentials cred) throws AuthenticationException {
		User foundUser = userRepository.findByUsername(cred.getUsername());
		if (foundUser != null && BCrypt.checkpw(cred.getPassword(), foundUser.getHash())) {
			return foundUser;
		} else {
			throw new AuthenticationException("Invalid username or password.");
		}
	}
}
