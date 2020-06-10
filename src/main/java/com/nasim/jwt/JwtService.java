package com.nasim.jwt;

import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JwtService {

	private final JwtConfig jwtConfig;
	private final SecretKey secretKey;

	@Autowired
	public JwtService(JwtConfig jwtConfig, SecretKey secretKey) {
		this.jwtConfig = jwtConfig;
		this.secretKey = secretKey;
	}

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		Claims claims = getClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	public Claims getClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(removeTokenPrefix(token)).getBody();
	}

	public String removeTokenPrefix(String token) {
		return token.replace(jwtConfig.getTokenPrefix(), ": ");
	}
	
	
}
