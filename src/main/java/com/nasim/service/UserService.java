package com.nasim.service;

import com.nasim.dto.UserDto;
import com.nasim.exception.Response;


public interface UserService {
	public Response getAllUserList(int page, int size);
	public Response updateUser(Long id, UserDto userDto);
	public Response getUser(Long id);
}
