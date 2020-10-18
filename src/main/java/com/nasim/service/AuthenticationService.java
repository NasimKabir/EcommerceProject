package com.nasim.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nasim.exception.MessageResponse;
import com.nasim.jwt.JwtTokenProvider;
import com.nasim.model.LoginRequest;
import com.nasim.model.LoginResponse;
import com.nasim.model.User;
import com.nasim.repository.UserRepository;
import com.nasim.security.UserDetailsImpl;

@Service
public class AuthenticationService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	public ResponseEntity<?> login(LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtTokenProvider.GenerateToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		if (authentication.isAuthenticated()) {
			LoginResponse loginResponse = new LoginResponse();
			loginResponse.setToken(jwt);
			loginResponse.setId(userDetails.getId());
			loginResponse.setUsername(userDetails.getUsername());
			//loginResponse.setEmail(userDetails.getEmail());
			loginResponse.setRoles(roles);
			return ResponseEntity.ok(new MessageResponse("User logged in successfully!"));
		}
		return ResponseEntity.badRequest()
				.body(new MessageResponse("Invalid Username or password"));
	}
	
	public ResponseEntity<?> registerUser(User user) {
		if (userRepository.existsByUsername(user.getUsername())) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		/*if (userRepository.existsByEmail(user.getEmail())) {
			return throw new RuntimeException("Error: Email is already in use!");
		}*/
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setIsActive(true);
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
