package com.nasim.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nasim.dto.UserDto;
import com.nasim.exception.Response;
import com.nasim.exception.ResponseBuilder;
import com.nasim.model.User;
import com.nasim.repository.UserRepository;
import com.nasim.service.UserService;



@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Response getAllUserList(int page, int size) {
		List<User> product = new ArrayList<User>();
		Pageable pagingSort = PageRequest.of(page, size);

		Page<User> pageTuts = userRepository.findAll(pagingSort);

		product = pageTuts.getContent();

		Map<String, Object> response = new HashMap<>();
		response.put("productList", product);
		response.put("currentPage", pageTuts.getNumber());
		response.put("totalItems", pageTuts.getTotalElements());
		response.put("totalPages", pageTuts.getTotalPages());
		response.put("nextPage", pageTuts.hasNext());

		return ResponseBuilder.getSuccessResponse(HttpStatus.OK, " retrieved Successfully", response);
	
	}

	@Override
	public Response updateUser(Long id, UserDto userDto) {
		User user = userRepository.findById(id).get();
		user=userRepository.save(user);
		if (user != null) {
			UserDto productDto = modelMapper.map(user, UserDto.class);
			if (productDto != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, " retrieved Successfully", userDto);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, " not found");
	}

	@Override
	public Response getUser(Long id) {
		User user = userRepository.findById(id).get();
		if (user != null) {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			UserDto productDto = modelMapper.map(user, UserDto.class);
			if (productDto != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, " retrieved Successfully", productDto);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, " not found");
	}

}
