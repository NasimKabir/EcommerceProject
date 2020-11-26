package com.nasim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nasim.dto.ProductDto;
import com.nasim.dto.UserDto;
import com.nasim.exception.Response;
import com.nasim.model.Product;
import com.nasim.service.ProductService;

@RestController
@RequestMapping("/api/v1/product")
@CrossOrigin
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@PostMapping
    public Response productInserted(@ModelAttribute ProductDto productDto, @RequestParam(value = "data") String data,
			@RequestParam("file") MultipartFile file){
	
                return productService.addNewProduct(productDto, data, file);
       
    }
 
	 
	 @PutMapping("/{id}")
	    public Response productUpdated(@RequestParam("id")Long id,@ModelAttribute ProductDto productDto,
				@RequestParam("file") MultipartFile file,BindingResult result){
		
	                return productService.updateProduct(id, productDto, file);
	       
	    }
	 
	
	    @GetMapping("/{id}")
	    public Response getProduct(@PathVariable("id") Long id) {
	        return productService.getProductId(id);
	    }

	    @GetMapping
	    public ResponseEntity<CollectionModel<UserDto>> getAllProduct(@RequestParam(required = false, defaultValue = "0") Integer page,
               @RequestParam(required = false, defaultValue = "3") Integer size,
               @RequestParam(required = false) String[] sort,
               @RequestParam(required = false, defaultValue = "asc") String dir) {

	        @SuppressWarnings("unchecked")
			CollectionModel<UserDto> userList = productService.getProductList(page, size, sort, dir);
	        if(userList != null) {
	            return ResponseEntity.ok(userList);
	        }
	        return ResponseEntity.noContent().build();
	    }
	

}
