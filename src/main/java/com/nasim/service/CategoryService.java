package com.nasim.service;

import com.nasim.dto.CategoryDto;
import com.nasim.exception.Response;

public interface CategoryService {
	public Response getCategoryList(int page, int size) ;
	public Response addNewCategory( CategoryDto categoryDto) ;
	public Response getCategoryId( Long id);
	public Response deleteCategory( Long id);
	public Response updateCategory( Long id,CategoryDto categoryDto);
}
