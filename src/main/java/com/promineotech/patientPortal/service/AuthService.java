package com.promineotech.patientPortal.service;

import java.security.Key;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.promineotech.patientPortal.entity.Credentials;
import com.promineotech.patientPortal.entity.User;
import com.promineotech.patientPortal.repository.UserRepository;
import com.promineotech.patientPortal.views.LoggedInView;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	
	private static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	public boolean isAdmin(String token) {
		return ((String)Jwts.parser()
				.setSigningKey(key)
				.parseClaimsJws(token)
				.getBody()
				.get("role"))
				.equals("ADMIN");
	}
	
	public User register(Credentials cred, String role) throws AuthenticationException {
		User user = new User();
		user.setRole(role);
		user.setUsername(cred.getUsername());
		user.setHash(BCrypt.hashpw(cred.getPassword(), BCrypt.gensalt()));
		try {
			userRepository.save(user);
			return user;
		} catch (DataIntegrityViolationException e) {
			throw new AuthenticationException ("Username not available.");
		}
	}

	public LoggedInView login(Credentials cred) throws AuthenticationException {
		User foundUser = userRepository.findByUsername(cred.getUsername());
		if (foundUser != null && BCrypt.checkpw(cred.getPassword(), foundUser.getHash())) {
			LoggedInView view = new LoggedInView();
			view.setUser(foundUser);
			view.setJwt(generateToken(foundUser));
			return view;
		} else {
			throw new AuthenticationException("Invalid username or password.");
		}
	}
	
	public boolean isCorrectUser(String jwt, Long userId) {
		return new Long((Integer)Jwts.parser()
				.setSigningKey(key)
				.parseClaimsJws(jwt)
				.getBody()
				.get("userId"))
				.equals(userId);
	}

	private String generateToken(User user) {
		String jwt = Jwts.builder()
				.claim("role", "USER")
				.claim("userId", user.getId())
				.setSubject("PROMINEO TECH JWT")
				.signWith(key)
				.compact();
			return jwt;
	}
	
	public String getToken(HttpServletRequest request) throws Exception {
		String header = request.getHeader("Authorization");
		if (header == null) {
			throw new Exception("Request contains no token.");
		}
		return header.replaceAll("Bearer ", "");
	}

}
