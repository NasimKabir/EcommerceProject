package com.nasim.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nasim.exception.Response;
import com.nasim.exception.ResponseException;
import com.nasim.model.LoginRequest;
import com.nasim.model.User;
import com.nasim.repository.RoleRepository;
import com.nasim.repository.UserRepository;
import com.nasim.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1")
public class UserController {
	@Autowired
	private AuthenticationService authenticationService;
	

	 @PostMapping("/login")
	    public ResponseEntity<?> login(@RequestBody LoginRequest login, HttpServletRequest request, HttpServletResponse response){
	        return authenticationService.login(login);
	    }
	  
	  @PostMapping("/signup")
	    public ResponseEntity<?> Register(@Valid @RequestBody User user, HttpServletRequest request, HttpServletResponse response){
	        return authenticationService.registerUser(user);
	    }



}
