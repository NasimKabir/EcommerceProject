package com.nasim.service;

import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.nasim.dto.ProductDto;
import com.nasim.exception.Response;
import com.nasim.model.Product;

public interface ProductService {
	public PagedModel getProductList(int page, int size, String[] sort, String dir) ;
	Response addNewProduct( ProductDto productdto, String data,MultipartFile file) ;
	public Response getProductId( Long id);
	public Response deleteProduct( Long id);
	public Response updateProduct( Long id,ProductDto productDto, MultipartFile file);
}
