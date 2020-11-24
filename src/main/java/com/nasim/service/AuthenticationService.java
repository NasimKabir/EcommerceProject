package com.nasim.service;

import com.nasim.dto.LoginRequest;
import com.nasim.dto.UserDto;
import com.nasim.exception.Response;


public interface AuthenticationService {

	public Response login(LoginRequest loginRequest);

	public Response registerUser(UserDto signUpRequest);
}
