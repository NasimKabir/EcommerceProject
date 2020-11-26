package com.nasim.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.nasim.controller.CategoryController;
import com.nasim.dto.CategoryDto;
import com.nasim.model.Category;
@Component
public class CategoryAssembler implements RepresentationModelAssembler<Category,CategoryDto > {

	@Override
	public CategoryDto toModel(Category entity) {
		CategoryDto categoryDto=new CategoryDto(entity.getName());
		categoryDto.add(linkTo(methodOn(CategoryController.class).getCategoryId(entity.getId())).withSelfRel());
		return categoryDto;
	}

}
