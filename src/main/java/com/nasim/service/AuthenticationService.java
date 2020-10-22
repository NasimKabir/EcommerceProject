package com.nasim.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nasim.exception.Response;
import com.nasim.exception.ResponseBuilder;
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
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private PasswordEncoder encoder;
	
	public Response login(LoginRequest loginRequest) {

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
			loginResponse.setEmail(userDetails.getEmail());
			loginResponse.setRoles(roles);
			return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "Logged In Success", loginResponse);
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.BAD_REQUEST, "Invalid Username or password");
	}

	public Response registerUser(User user) {
		if (userRepository.existsByUsername(user.getUsername())) {
			return ResponseBuilder.getFailureResponse(HttpStatus.BAD_REQUEST, "Error: Username is already taken!");
		}
		
		  if (userRepository.existsByEmail(user.getEmail())) { return
		  ResponseBuilder.getFailureResponse(HttpStatus.BAD_REQUEST,"Error: Email is already in use!"); 
		  }
		 
       user.setPassword(encoder.encode(user.getPassword()));
       user.setIsActive(true);
		userRepository.save(user);

		return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "User registered successfully!");

	}
}
