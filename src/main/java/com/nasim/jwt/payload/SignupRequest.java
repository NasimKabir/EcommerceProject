package com.nasim.jwt.payload;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.nasim.model.Role;

import lombok.Data;

@Data
public class SignupRequest {
	@NotBlank
	@Size(min = 4, max = 15)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	private Set<String> role;

	@NotBlank
	//@Size(min = 4, max = 15)
	private String password;
	
	@NotBlank
	//@Size( max = 11, message = "digits should be 11")
	private String phone;

}
