package com.nasim.controller;

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
	
	@GetMapping(path="/id")
	public String findID(@RequestParam(value="id") int id){
		String categoryName=categoryRepository.findById(id).get().getName();
		System.out.println("category name is = "+categoryName); 
		
		return categoryName;
	}

	@PostMapping(path = "/products")
	public ResponseEntity<?> addNewProduct(@RequestParam(value = "data") String data,@RequestParam("file") MultipartFile file) throws Exception {

		if (file == null || file.getOriginalFilename() == null) {
			System.out.println("File not found");
		}
		System.out.println(data);
		Product product = new Product();
		
		String categoryName=categoryRepository.findById(1).get().getName();
		System.out.println("catgory name is = "+categoryName); 
		
		
		product = FileUploadUtil.convertStringToProduct(data);

		
		FileUploadUtil.uploadFile(file, categoryName, product.getProductName(), defaultFilePath);

		String staticPath = FileUploadUtil.creatStaticPath(product.getCategories().getClass().getName(),
				product.getProductName(), file.getOriginalFilename());
		product.setImagePath(staticPath);
		productRepository.save(product);

		return new ResponseEntity<Product>(product, HttpStatus.CREATED);

	}

}
