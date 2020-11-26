package com.nasim.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.nasim.assembler.ProductAssembler;
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
	private ProductAssembler productAssembler;
	@SuppressWarnings("rawtypes")
	@Autowired
	private PagedResourcesAssembler pagedResourcesAssembler;
	@Autowired
	private ModelMapper modelMapper;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public PagedModel getProductList(int page, int size, String[] sort, String dir) {
		PageRequest pageRequest;
		Sort.Direction direction;
		if (sort == null) {
			pageRequest = PageRequest.of(page, size);
		} else {
			if (dir.equalsIgnoreCase("asc"))
				direction = Sort.Direction.ASC;
			else
				direction = Sort.Direction.DESC;
			pageRequest = PageRequest.of(page, size, Sort.by(direction, sort));
		}
		Page<Product> product = productRepository.findAll(pageRequest);
		if (!CollectionUtils.isEmpty(product.getContent())) {
			return pagedResourcesAssembler.toModel(product, productAssembler);
		}
		return null;
	}

	@Override
	public Response addNewProduct(ProductDto productdto, String data, MultipartFile file) {
		Product product = modelMapper.map(productdto, Product.class);
		String categoryName = categoryRepository.findAll().listIterator().next().getName();
		product = FileUploadUtil.convertStringToProduct(data);

		try {
			FileUploadUtil.uploadFile(file, categoryName, product.getProductName(), defaultFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String staticPath = FileUploadUtil.creatStaticPath(product.getCategories().getClass().getName(),
				product.getProductName(), file.getOriginalFilename());
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
