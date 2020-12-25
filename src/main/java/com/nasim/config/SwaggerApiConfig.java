package com.nasim.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerApiConfig implements WebMvcConfigurer {

	@Bean
	public Docket Api() {
		return new Docket(DocumentationType.SWAGGER_2).select().paths(regex("/api.*")).build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Ecommerce API Documentation")
				.description("Ecommerce is a web application designed for project management").build();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		 registry.
	        addResourceHandler( "/swagger-ui/**")
	        .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
	}
	

}
