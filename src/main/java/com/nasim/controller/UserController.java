package com.nasim.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nasim.exception.UserNotFoundException;
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

	@PostMapping("/users")
	public ResponseEntity<User> createdUser(@Valid @RequestBody User user) {
		if (userRepository.existsByUsername(user.getUsername())) {
			throw new RuntimeException(user.getUsername() + " doesn't exists !");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

	@GetMapping("/users")
	public ResponseEntity<Page<User>> getUserList(Pageable pageable) {
		Page<User> allUser = userRepository.findAll(pageable);
		return new ResponseEntity<Page<User>>(allUser, HttpStatus.OK);

	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User>findOne(@PathVariable("id")int id){
		User user=userRepository.findById(id)
				  .orElseThrow(()->new UserNotFoundException("User Id - "+id+" Not found."));
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	

}
