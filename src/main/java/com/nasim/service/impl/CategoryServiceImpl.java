package com.nasim.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.nasim.assembler.CategoryAssembler;
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
	private CategoryAssembler categoryAssembler;
	@SuppressWarnings("rawtypes")
	@Autowired
	private PagedResourcesAssembler pagedResourcesAssembler;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PagedModel getCategoryList(int page, int size, String[] sort, String dir) {
		PageRequest pageRequest = null;
		Sort.Direction direction;
		if(sort==null) {
			pageRequest=PageRequest.of(page, size);
		}else {
			if(dir.equalsIgnoreCase("asc")) {
				direction=Sort.Direction.ASC;
			}else {
				direction=Sort.Direction.DESC;
				pageRequest=PageRequest.of(page, size, Sort.by(direction, sort));
			}
		}
		Page<Category>category=categoryRepository.findAll(pageRequest);
		if(!CollectionUtils.isEmpty(category.getContent())) {
			return pagedResourcesAssembler.toModel(category,categoryAssembler);
		}
		return null;
	}

	@Override
	public Response addNewCategory(CategoryDto categoryDto) {
		Category category=modelMapper.map(categoryDto, Category.class);
		if (categoryRepository.existsByName(category.getName())) {
			throw new RuntimeException(category.getName() + " doesn't exists !");
		}
		if(category !=null) {
			category.setIsActive(true);
			categoryRepository.save(category);
			return ResponseBuilder.getSuccessResponse(HttpStatus.CREATED,"Category created successfully done!");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
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
