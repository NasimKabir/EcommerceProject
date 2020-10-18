package com.nasim.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nasim.exception.ResponseException;
import com.nasim.model.Category;
import com.nasim.repository.CategoryRepository;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {
	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping("/category")
	public ResponseEntity<List<Category>> getRolesList() {
		List<Category> category = categoryRepository.findAll();
		return new ResponseEntity<List<Category>>(category, HttpStatus.OK);

	}
	@PostMapping("/category")
	public ResponseEntity<Category> createdPost( @RequestBody Category category) {
		if (categoryRepository.existsByName(category.getName())) {
			throw new RuntimeException(category.getName() + " doesn't exists !");
		}
		category.setIsActive(true);
		categoryRepository.save(category);
		return new ResponseEntity<Category>(category, HttpStatus.CREATED);
	}
	
	@GetMapping(path = "/category/{id}")
	public ResponseEntity<?> getCategoryId(@PathVariable("id") int id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResponseException("category id is = " + id + " not found."));
		return new ResponseEntity<Category>(category, HttpStatus.OK);
	}

	@DeleteMapping(path = "/category/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable("id") int id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResponseException("category id is = " + id + " not found."));
		return new ResponseEntity<Category>(category, HttpStatus.OK);
	}

	@PutMapping(path = "/category/{id}")
	public ResponseEntity<?> updateCategory(@PathVariable("id") int id,@Valid @RequestBody Category category) {

		Category categoryUpdated = categoryRepository.findById(id)
				.orElseThrow(() -> new ResponseException("category id is = " + id + " not found."));

		categoryUpdated.setName(category.getName());

		categoryRepository.save(categoryUpdated);

		return new ResponseEntity<Category>(categoryUpdated, HttpStatus.CREATED);

	}

}
