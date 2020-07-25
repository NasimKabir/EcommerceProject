package com.nasim.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.nasim.exception.FileStorageException;
import com.nasim.exception.ProductNotFoundException;
import com.nasim.model.Category;
import com.nasim.model.Product;
import com.nasim.model.User;
import com.nasim.repository.CategoryRepository;
import com.nasim.repository.ProductRepository;
import com.nasim.util.FileUploadUtil;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
	@Value("${file.upload}")
	private String defaultFilePath;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping("/products")
	public ResponseEntity<Page<Product>> getProductList(Pageable pageable) {
		Page<Product> product = productRepository.findAll(pageable);
		return new ResponseEntity<Page<Product>>(product, HttpStatus.OK);

	}

	@PostMapping(path = "/products")
	public ResponseEntity<?> addNewProduct(@Valid @ModelAttribute Product product, @RequestParam(value = "data") String data,@RequestParam("file") MultipartFile file) throws Exception {

		String categoryName=categoryRepository.findAll().listIterator().next().getName();
		 product = FileUploadUtil.convertStringToProduct(data);
		  
		  FileUploadUtil.uploadFile(file, categoryName, product.getProductName(), defaultFilePath);
		  
		  String staticPath = FileUploadUtil.creatStaticPath(product.getCategories().getClass().getName(), product.getProductName(), file.getOriginalFilename());
		  product.setImagePath(staticPath);
		  productRepository.save(product);
		 
		return new ResponseEntity<Product>(product, HttpStatus.CREATED);

	}
	
	@GetMapping(path = "/products/{id}")
	public ResponseEntity<?>getProductId(@PathVariable("id") int id){
		Product product=productRepository.findById(id)
				.orElseThrow(()->new ProductNotFoundException("Product id "+id+" not found."));
		System.out.println("----------- ********** "+product);
		return new ResponseEntity<Product>(product,HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/products/{id}")
	public ResponseEntity<?>deleteProduct(@PathVariable("id") int id){
		Product product=productRepository.findById(id)
				.orElseThrow(()->new ProductNotFoundException("Product id "+id+" not found."));
		productRepository.deleteById(id);
		System.out.println("----------- ********** "+product);
		return new ResponseEntity<Product>(product,HttpStatus.OK);
	}

}
