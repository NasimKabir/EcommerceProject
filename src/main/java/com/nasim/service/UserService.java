package com.nasim.service;

import org.springframework.hateoas.PagedModel;

import com.nasim.dto.UserDto;
import com.nasim.exception.Response;


public interface UserService {
	public PagedModel getAllUserList(int page, int size, String[] sort, String dir);
	public Response updateUser(Long id, UserDto userDto);
	public Response getUser(Long id);
}
