package com.nasim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nasim.exception.ResponseException;
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
				.orElseThrow(()->new ResponseException("Product id "+id+" not found."));
		return new ResponseEntity<Product>(product,HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/products/{id}")
	public ResponseEntity<?>deleteProduct(@PathVariable("id") int id){
		Product product=productRepository.findById(id)
				.orElseThrow(()->new ResponseException("Product id "+id+" not found."));
		productRepository.deleteById(id);
		return new ResponseEntity<Product>(product,HttpStatus.OK);
	}
	
	@PutMapping(path = "/products/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable("id") int id, @RequestBody Product product) throws Exception {

	Product	updatedProduct=productRepository.findById(id)
				.orElseThrow(()->new ResponseException("Product id "+id+" not found."));
		
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
