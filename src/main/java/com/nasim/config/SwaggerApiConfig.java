package com.nasim.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerApiConfig {
	/*
	 * public static final Contact DEFAULT_CONTACT = new Contact("Nasim Kabir",
	 * "www.nasim.com", "nasim@gmail.com");
	 * 
	 * @SuppressWarnings("rawtypes") public static final ApiInfo DEFAULT = new
	 * ApiInfo("Awesome Api Title", "Awesome Api Documentation", "1.0", "urn:tos",
	 * DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0",
	 * new ArrayList<VendorExtension>());
	 * 
	 * public static Set<String> DEFAULT_APPLICATION_CONSUMES = new HashSet<>(
	 * Arrays.asList("application/json", "application/xml"));
	 * 
	 * @Bean // @Primary public Docket api() {
	 * 
	 * return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT).consumes(
	 * DEFAULT_APPLICATION_CONSUMES) .produces(DEFAULT_APPLICATION_CONSUMES); }
	 */
	
	 @Bean
	    public Docket api() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .apiInfo(apiInfo())
	                .select()
	                .apis(RequestHandlerSelectors.any())
	                .paths(PathSelectors.any())
	                .build();
	    }

	    private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                .title("Información")
	                .build();
	    }
}
