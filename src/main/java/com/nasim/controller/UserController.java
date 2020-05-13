package com.nasim.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nasim.model.User;
import com.nasim.repository.RoleRepository;
import com.nasim.repository.UserRepository;

@RestController
@RequestMapping("/api/v1")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	RoleRepository roleRepository;

	@PostMapping("create")
	public ResponseEntity<User> createdPost(@Valid @RequestBody User user, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);
		} else if (userRepository.existsByUsername(user.getUsername())) {
			return new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

}
