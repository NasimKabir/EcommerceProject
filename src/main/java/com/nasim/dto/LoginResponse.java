package com.nasim.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
public class LoginResponse {
	@JsonInclude(JsonInclude.Include.ALWAYS)
	private String token;
	
	@JsonInclude(JsonInclude.Include.ALWAYS)
	private String type = "Bearer";
	
	@JsonInclude(JsonInclude.Include.ALWAYS)
	private Long id;
	
	@JsonInclude(JsonInclude.Include.ALWAYS)
	private String username;
	
	@JsonInclude(JsonInclude.Include.ALWAYS)
	private String email;
	
	@JsonInclude(JsonInclude.Include.ALWAYS)
	private List<String> roles;
}