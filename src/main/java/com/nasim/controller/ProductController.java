package com.nasim.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
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
	public ResponseEntity<?> addNewProduct( @ModelAttribute Product product, @RequestParam(value = "data") String data,@RequestParam("file") MultipartFile file) throws Exception {

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
		return new ResponseEntity<Product>(product,HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/products/{id}")
	public ResponseEntity<?>deleteProduct(@PathVariable("id") int id){
		Product product=productRepository.findById(id)
				.orElseThrow(()->new ProductNotFoundException("Product id "+id+" not found."));
		productRepository.deleteById(id);
		return new ResponseEntity<Product>(product,HttpStatus.OK);
	}
	
	@PutMapping(path = "/products/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable("id") int id, @RequestBody Product product) throws Exception {

	Product	updatedProduct=productRepository.findById(id)
				.orElseThrow(()->new ProductNotFoundException("Product id "+id+" not found."));
		
		 if(updatedProduct == null){
	            return null;
	        }

	        updatedProduct.setProductName(product.getProductName());
	        updatedProduct.setPrice(product.getPrice());
	        updatedProduct.setGender(product.getGender());
	        updatedProduct.setDescription(product.getDescription());
	        updatedProduct.setProductDetails(product.getProductDetails());
	        
		  productRepository.save(updatedProduct);
		 
		return new ResponseEntity<Product>(updatedProduct, HttpStatus.CREATED);
	
	}
	
	

}
