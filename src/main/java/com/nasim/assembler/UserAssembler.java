package com.nasim.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.nasim.controller.UserController;
import com.nasim.dto.UserDto;
import com.nasim.model.User;

@Component
public class UserAssembler implements RepresentationModelAssembler<User,UserDto>{

	@Override
	public UserDto toModel(User entity) {
		UserDto user=new UserDto(entity.getUsername(),entity.getEmail(),entity.getPhone());
		user.add(linkTo(methodOn(UserController.class).get(entity.getId())).withSelfRel());
		return user;
	}

}
