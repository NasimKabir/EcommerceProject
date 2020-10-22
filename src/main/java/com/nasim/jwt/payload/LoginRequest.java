package com.nasim.jwt.payload;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginRequest {
	@NotBlank(message = "Username mandatory")
    private String username;
    @NotBlank(message = "Password mandatory")
    private String password;
}