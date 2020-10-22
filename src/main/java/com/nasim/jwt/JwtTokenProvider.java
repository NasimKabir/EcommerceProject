package com.nasim.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.nasim.security.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;


@Component
public class JwtTokenProvider {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	private String secretkey="SpringbootTutorials2020";
	private Long expirationInMillis=Long.valueOf("4");

	public String GenerateToken(Authentication authentication) {
		UserDetailsImpl detailsImpl=(UserDetailsImpl) authentication.getPrincipal();
		return Jwts.builder()
				.setSubject(detailsImpl.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(JwtUtils.getExpirationTimeHourly(expirationInMillis))
				.signWith(SignatureAlgorithm.HS512, secretkey)
				.compact();
		
	}

	public String getUsernameFormJwtToken(String token) {
		return Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String token) {
		try {
		Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token);
		return true;
		}catch(SignatureException e) {
			logger.error("Invalid jwt signature {}", e.getMessage());
		}catch(MalformedJwtException e) {
			logger.error("Invalid Jwt token {}", e.getMessage());
		}catch(ExpiredJwtException e) {
			logger.error("Jwt token expired {}", e.getMessage());
		}catch(UnsupportedJwtException e) {
			logger.error("Jwt token is unsupported {}", e.getMessage());
		}catch(IllegalArgumentException e) {
			logger.error("Jwt claims string is empty {}", e.getMessage());
		}
		return false;
	}
}
