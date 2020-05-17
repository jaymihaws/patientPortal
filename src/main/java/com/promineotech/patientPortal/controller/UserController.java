package com.promineotech.patientPortal.controller;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.patientPortal.entity.Credentials;
import com.promineotech.patientPortal.service.AuthService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private AuthService authService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<Object> register(@RequestBody Credentials cred) {
		try {
			return new ResponseEntity<Object>(authService.register(cred), HttpStatus.CREATED);
		} catch (AuthenticationException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Object> login(@RequestBody Credentials cred) {
		try {
			return new ResponseEntity<Object>(authService.login(cred), HttpStatus.OK);
		} catch (AuthenticationException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}
//	
//	@RequestMapping(value = "/adminregister", method = RequestMethod.POST)
//	public ResponseEntity<Object> adminregister(@RequestBody Admin admin, HttpServletRequest request) {
//		return new ResponseEntity<Object>(service.createAdmin(admin), HttpStatus.CREATED);
//	}
//
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public ResponseEntity<Object> admin(@RequestBody Admin patient) {
//		try {
//			return new ResponseEntity<Object>(service.login(admin), HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
//		}
//	}

}
