package com.nasim.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nasim.exception.StorageFileNotFoundException;
import com.nasim.model.Category;
import com.nasim.model.Product;
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

	

	@PostMapping(path = "/products")
	public ResponseEntity<?> addNewProduct( @ModelAttribute Product product, @RequestParam(value = "data") String data,
			@RequestParam("file") MultipartFile file) throws Exception {

		if (file == null || file.getOriginalFilename() == null) {
			throw new StorageFileNotFoundException(file.getName()+" Not Found, Please select your file");
		}
        
		String categoryName=categoryRepository.findAll().listIterator().next().getName();
		
		  product = FileUploadUtil.convertStringToProduct(data);
		  
		  
		  FileUploadUtil.uploadFile(file, categoryName, product.getProductName(), defaultFilePath);
		  
		  String staticPath = FileUploadUtil.creatStaticPath(product.getCategories().getClass().getName(), product.getProductName(), file.getOriginalFilename());
		  product.setImagePath(staticPath);
		  productRepository.save(product);
		 

		return new ResponseEntity<Product>(product, HttpStatus.CREATED);

	}

}
