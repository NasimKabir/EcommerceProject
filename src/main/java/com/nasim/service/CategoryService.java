package com.nasim.service;

import org.springframework.hateoas.PagedModel;

import com.nasim.dto.CategoryDto;
import com.nasim.exception.Response;

public interface CategoryService {
	public PagedModel getCategoryList(int page, int size, String[] sort, String dir) ;
	Response addNewCategory( CategoryDto categoryDto) ;
	public Response getCategoryId( Long id);
	public Response deleteCategory( Long id);
	public Response updateCategory( Long id,CategoryDto categoryDto);
}
