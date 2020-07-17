package com.nasim.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/name")
	public ResponseEntity<?>FindCategoryName(){
		Category category=new Category();
		List<Category> categoryName = categoryRepository.findAll();
		System.out.println(" Product categoryName is " + categoryName);
		return new ResponseEntity<Category>(category, HttpStatus.OK);
	}
}
