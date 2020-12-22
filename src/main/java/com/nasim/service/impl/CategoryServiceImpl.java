package com.nasim.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nasim.dto.CategoryDto;
import com.nasim.exception.Response;
import com.nasim.exception.ResponseBuilder;
import com.nasim.model.Category;
import com.nasim.repository.CategoryRepository;
import com.nasim.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Response getCategoryList(int page, int size) {
		List<Category> product = new ArrayList<Category>();
		Pageable pagingSort = PageRequest.of(page, size);

		Page<Category> pageTuts = categoryRepository.findAll(pagingSort);

		product = pageTuts.getContent();

		Map<String, Object> response = new HashMap<>();
		response.put("productList", product);
		response.put("currentPage", pageTuts.getNumber());
		response.put("totalItems", pageTuts.getTotalElements());
		response.put("totalPages", pageTuts.getTotalPages());
		response.put("nextPage", pageTuts.hasNext());

		return ResponseBuilder.getSuccessResponse(HttpStatus.OK, " retrieved Successfully", response);
	}

	@Override
	public Response addNewCategory(CategoryDto categoryDto) {
		Category category=modelMapper.map(categoryDto, Category.class);
		if (categoryRepository.existsByName(category.getName())) {
			throw new RuntimeException(category.getName() + " doesn't exists !");
		}
		categoryRepository.save(category);
		return ResponseBuilder.getSuccessResponse(HttpStatus.CREATED, "Category created successfully done!");
	}

	@Override
	public Response getCategoryId(Long id) {
		Category category=categoryRepository.findById(id).get();
		CategoryDto categoryDto=modelMapper.map(category, CategoryDto.class);
		if(categoryDto !=null) {
			return ResponseBuilder.getSuccessResponse(HttpStatus.OK, " retrieved Successfully", categoryDto);
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, " not found");
	}

	@Override
	public Response deleteCategory(Long id) {
		Category category=categoryRepository.findById(id).get();
		CategoryDto categoryDto=modelMapper.map(category, CategoryDto.class);
		if(categoryDto !=null) {
			categoryRepository.deleteById(id);
			return ResponseBuilder.getSuccessResponse(HttpStatus.OK, " Category deleted Successfull");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, " not found");
	}

	@Override
	public Response updateCategory(Long id, CategoryDto categoryDto) {
		Category category=categoryRepository.findById(id).get();
		if(category !=null) {
			 categoryDto=modelMapper.map(category, CategoryDto.class);
			 if(categoryDto !=null) {
				 category.setName(category.getName());
				 categoryRepository.save(category);
				 return ResponseBuilder.getSuccessResponse(HttpStatus.OK, " updated Successfully", category);
			 }
			 return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
		}
		
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, " not found");
	}

}
