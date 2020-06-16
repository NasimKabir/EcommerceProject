package com.nasim.jwt;

import javax.crypto.SecretKey;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import io.jsonwebtoken.security.Keys;

@Configuration
@ConfigurationProperties(prefix = "application.jwt")
public class JwtConfig {
	private String secretKey;
	private String tokenPrefix;
	private Integer tokenExpirationAfterDays;

	public JwtConfig() {
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getTokenPrefix() {
		return tokenPrefix;
	}

	public void setTokenPrefix(String tokenPrefix) {
		this.tokenPrefix = tokenPrefix;
	}

	public Integer getTokenExpirationAfterDays() {
		return tokenExpirationAfterDays;
	}

	public void setTokenExpirationAfterDays(Integer tokenExpirationAfterDays) {
		this.tokenExpirationAfterDays = tokenExpirationAfterDays;
	}

	// configuration header
	public String getAuthorizationHeader() {
		return HttpHeaders.AUTHORIZATION;
	}

	@Bean
	public SecretKey secretKey() {
		return Keys.hmacShaKeyFor(getSecretKey().getBytes());
	}
}
