package com.nasim.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
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
import com.nasim.exception.Response;
import com.nasim.service.ProductService;
import com.nasim.util.FileUploadUtil;


@RestController
@RequestMapping("/api/v1/product")
@CrossOrigin("http://localhost:3000")
public class ProductController {
	@Value("${file.upload}")
	private String defaultFilePath;

	@Autowired
	private ProductService productService;

	@PostMapping
	public Response productInserted(@ModelAttribute ProductDto productDto, @RequestParam(value = "data") String data,
			@RequestParam("file") MultipartFile file) {

		return productService.addNewProduct(productDto, data, file);

	}

	@PutMapping("/{id}")
	public Response productUpdated(@RequestParam("id") Long id, @ModelAttribute ProductDto productDto,
			@RequestParam("file") MultipartFile file, BindingResult result) {

		return productService.updateProduct(id, productDto, file);

	}

	@GetMapping("/{id}")
	public Response getProduct(@PathVariable("id") Long id) {
		return productService.getProductId(id);
	}

	@GetMapping
	public Response getAllProduct(@RequestParam(required = false, defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = "9") Integer size) {

		return productService.getProductList(page, size);
	}

	@GetMapping("/{category}/{product}/{fileName:.+}")
	public void downloadImage(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("category") String category, @PathVariable("product") String product,
			@PathVariable("fileName") String fileName) throws IOException {
		File file = new File(FileUploadUtil.creatStaticURL(defaultFilePath, category, product, fileName));
		 System.out.println("++++++++++++++++"+file);
		if (file.exists()) {
			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
			 System.out.println("//////////////"+mimeType);
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}
			response.setContentType(mimeType);
			// eg Content-Disposition: inline; filename="filename.pdf"
			response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
			response.setContentLength((int) file.length());
			 System.out.println("****************"+response);
			InputStream inputStream;
			try {
				inputStream = new BufferedInputStream(new FileInputStream(file));
				 System.out.println("------------------"+inputStream);
				FileCopyUtils.copy(inputStream, response.getOutputStream());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

}
