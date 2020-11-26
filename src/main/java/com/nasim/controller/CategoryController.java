package com.nasim.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.nasim.exception.ResponseException;
import com.nasim.model.Category;
import com.nasim.repository.CategoryRepository;
import com.nasim.service.impl.CategoryServiceImpl;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
	@Autowired
	private CategoryServiceImpl  categoryServiceImpl;
	
	@GetMapping
	public ResponseEntity<CollectionModel<CategoryDto>>  getCategoryList(@RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "3") Integer size,
            @RequestParam(required = false) String[] sort,
            @RequestParam(required = false, defaultValue = "asc") String dir) {
		
		@SuppressWarnings("unchecked")
		CollectionModel<CategoryDto> categoryList = categoryServiceImpl.getCategoryList(page, size, sort, dir);
		if(categoryList !=null) {
			return ResponseEntity.ok(categoryList);
		}
		return ResponseEntity.noContent().build();

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
