package com.nasim.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nasim.dto.ProductDto;
import com.nasim.dto.UserDto;
import com.nasim.exception.Response;
import com.nasim.exception.ResponseBuilder;
import com.nasim.model.Product;
import com.nasim.repository.CategoryRepository;
import com.nasim.repository.ProductRepository;
import com.nasim.service.ProductService;
import com.nasim.util.FileUploadUtil;

@Service
public class ProductServiceImpl implements ProductService {
	@Value("${file.upload}")
	private String defaultFilePath;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;


	@Override
	public Response getProductList(int page, int size) {
		List<Product> product = new ArrayList<Product>();
		Pageable pagingSort = PageRequest.of(page, size);

		Page<Product> pageTuts = productRepository.findAll(pagingSort);

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
	public Response addNewProduct(ProductDto productdto, String data, MultipartFile file) {
		Product product = modelMapper.map(productdto, Product.class);
		String categoryName = categoryRepository.findAll().listIterator().next().getName();
		product = FileUploadUtil.convertStringToProduct(data);
		System.out.println(data);
		try {
			FileUploadUtil.uploadFile(file, categoryName, product.getProductName(), defaultFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String staticPath = FileUploadUtil.creatStaticPath(categoryName, product.getProductName(),
				file.getOriginalFilename());
		product.setImagePath(staticPath);
		productRepository.save(product);

		return ResponseBuilder.getSuccessResponse(HttpStatus.CREATED, " Product created Successfully", product);

	}

	@Override
	public Response getProductId(Long id) {
		Product product = productRepository.findById(id).get();
		if (product != null) {
			ProductDto productDto = modelMapper.map(product, ProductDto.class);
			if (productDto != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, " retrieved Successfully", productDto);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");

		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, " not found");
	}

	@Override
	public Response deleteProduct(Long id) {
		Product product = productRepository.findById(id).get();
		if (product != null) {
			UserDto productDto = modelMapper.map(product, UserDto.class);
			if (productDto != null) {
				productRepository.deleteById(id);
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "Product deleted Successfully", productDto);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");

		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, " not found");

	}

	@Override
	public Response updateProduct(Long id, ProductDto productDto, MultipartFile file) {
		Product product = productRepository.findById(id).get();
		if (product == null) {
			return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, " not found");
		}
		productDto = modelMapper.map(product, ProductDto.class);
		product.setProductName(product.getProductName());
		product.setPrice(product.getPrice());
		product.setGender(product.getGender());
		product.setDescription(product.getDescription());
		product.setProductDetails(product.getProductDetails());
		product.setImagePath(product.getImagePath());

		productRepository.save(product);
		return ResponseBuilder.getSuccessResponse(HttpStatus.OK, " retrieved Successfully", productDto);

	}

	
}
