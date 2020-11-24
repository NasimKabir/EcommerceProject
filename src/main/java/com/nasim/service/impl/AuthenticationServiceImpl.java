package com.nasim.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nasim.dto.LoginRequest;
import com.nasim.dto.LoginResponse;
import com.nasim.dto.UserDto;
import com.nasim.exception.Response;
import com.nasim.exception.ResponseBuilder;
import com.nasim.jwt.JwtTokenProvider;
import com.nasim.model.ERole;
import com.nasim.model.Role;
import com.nasim.model.User;
import com.nasim.repository.RoleRepository;
import com.nasim.repository.UserRepository;
import com.nasim.security.UserDetailsImpl;
import com.nasim.service.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
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

	public Response registerUser(UserDto userDto) {
		if (userRepository.existsByUsername(userDto.getUsername())) {
			return ResponseBuilder.getFailureResponse(HttpStatus.BAD_REQUEST, "Error: Username is already taken!");
		}

		if (userRepository.existsByEmail(userDto.getEmail())) {
			return ResponseBuilder.getFailureResponse(HttpStatus.BAD_REQUEST, "Error: Email is already in use!");
		}
		// Create new user's account
		User user = new User(userDto.getUsername(), userDto.getEmail(), userDto.getPhone(),
				encoder.encode(userDto.getPassword()));
		Set<String> strRoles = userDto.getRole();
		Set<Role> role = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			role.add(userRole);
		} else {

			Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
					.orElseThrow(() -> new RuntimeException("Error: Admin Role is not found."));
			role.add(adminRole);
		}

		user.setRoles(role);
		user.setIsActive(true);
		userRepository.save(user);

		return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "User registered successfully!");

	}
}
