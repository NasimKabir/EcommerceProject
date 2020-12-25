package com.nasim.service;

import org.springframework.web.multipart.MultipartFile;

import com.nasim.dto.ProductDto;
import com.nasim.exception.Response;

public interface ProductService {
	public Response getProductList(int page, int size) ;
	public Response addNewProduct( ProductDto productdto, String data,MultipartFile file) ;
	public Response getProductId( Long id);
	public Response deleteProduct( Long id);
	public Response updateProduct( Long id,ProductDto productDto, MultipartFile file);
	
}
