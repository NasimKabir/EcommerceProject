package com.nasim.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nasim.dto.CategoryDto;
import com.nasim.exception.Response;
import com.nasim.service.impl.CategoryServiceImpl;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
	@Autowired
	private CategoryServiceImpl  categoryServiceImpl;
	
	  @GetMapping
	    public Response getCategoryList(
	    		@RequestParam(required = false, defaultValue = "0") Integer page,
              @RequestParam(required = false, defaultValue = "3") Integer size) {

	        return categoryServiceImpl.getCategoryList(page, size);
	    }
	
	@PostMapping 
	public Response createdCategory( @RequestBody CategoryDto categoryDto) {
		return categoryServiceImpl.addNewCategory(categoryDto);
	}
	
	@GetMapping(path = "/{id}")
	public Response getCategoryId(@PathVariable("id") Long id) {
		return categoryServiceImpl.getCategoryId(id);
	}

	@DeleteMapping(path = "/{id}")
	public Response deleteCategory(@PathVariable("id") Long id) {
		return categoryServiceImpl.deleteCategory(id);
	}

	@PutMapping(path = "/{id}")
	public Response updateCategory(@PathVariable("id") Long id,@Valid @RequestBody CategoryDto categoryDto) {
		return categoryServiceImpl.updateCategory(id, categoryDto);

	}

}
