package com.nasim.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nasim.dto.LoginRequest;
import com.nasim.dto.UserDto;
import com.nasim.exception.Response;
import com.nasim.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class AuthenticationController {
	@Autowired
	private AuthenticationService authenticationService;
	
	 @PostMapping("/login")
	    public Response login(@RequestBody LoginRequest login, HttpServletRequest request, HttpServletResponse response){
	        return authenticationService.login(login);
	    }
	  
	  @PostMapping("/signup")
	    public Response Register(@Valid @RequestBody UserDto signUpRequest, HttpServletRequest request, HttpServletResponse response){
	        return authenticationService.registerUser(signUpRequest);
	    }



}
