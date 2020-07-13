package com.nasim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nasim.model.Product;
import com.nasim.repository.ProductRepository;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
	@Autowired
	private ProductRepository productRepository;

	

	@PostMapping(path="/products")
	public ResponseEntity<?> addNewProduct(@RequestBody Product product,@RequestParam MultipartFile file) {

		productRepository.save(product);
		
		

		return new ResponseEntity<Product>(product, HttpStatus.CREATED);

	}
	
	
   
}
