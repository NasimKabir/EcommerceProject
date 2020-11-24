package com.nasim.service.impl;


import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.nasim.assembler.UserAssembler;
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
	@Autowired
	private UserAssembler userAssembler;
	@SuppressWarnings("rawtypes")
	@Autowired
	private PagedResourcesAssembler pagedResourcesAssembler;

	@SuppressWarnings("rawtypes")
	@Override
	public PagedModel getAllUserList(int page, int size, String[] sort, String dir) {
		PageRequest pageRequest;
		Sort.Direction direction;
		if (sort == null) {
			pageRequest = PageRequest.of(page, size);
		} else {
			if (dir.equalsIgnoreCase("asc"))
				direction = Sort.Direction.ASC;
			else
				direction = Sort.Direction.DESC;
			pageRequest = PageRequest.of(page, size, Sort.by(direction, sort));
		}
		Page<User> user = userRepository.findAll(pageRequest);
		if (!CollectionUtils.isEmpty(user.getContent())) {
			return pagedResourcesAssembler.toModel(user, userAssembler);
		}
		return null;
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
