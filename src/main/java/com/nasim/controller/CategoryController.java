package com.nasim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nasim.model.Category;
import com.nasim.repository.CategoryRepository;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {
	@Autowired
	private CategoryRepository categoryRepository;
	
	@PostMapping("/category")
	public ResponseEntity<Category> createdPost( @RequestBody Category category) {
		if (categoryRepository.existsByName(category.getName())) {
			throw new RuntimeException(category.getName() + " doesn't exists !");
		}
		categoryRepository.save(category);
		return new ResponseEntity<Category>(category, HttpStatus.CREATED);
	}
}
