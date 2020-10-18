package com.nasim.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nasim.security.CustomUserDetailsService;

public class JwtTokenFilter extends OncePerRequestFilter {
private Logger logger=LoggerFactory.getLogger(this.getClass());
@Autowired
private CustomUserDetailsService userDetailsService;
@Autowired
private JwtTokenProvider jwttokenProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String jwt=ParseJwt(request);
			if(jwt !=null && jwttokenProvider.validateJwtToken(jwt)) {
				String username=jwttokenProvider.getUsernameFormJwtToken(jwt);
				UserDetails userDetails=userDetailsService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authenticaion=new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
				authenticaion.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticaion);
	
			}
		}catch(Exception e) {
			logger.error("Can not set user authentication: {}",e.getMessage());
		}
		filterChain.doFilter(request, response);
	}
	
	private  String ParseJwt(HttpServletRequest request) {
		String header=request.getHeader("Authorization");
		if(StringUtils.hasText(header)&& header.startsWith("Bearer")) {
			return header.substring(7);
		}
		return null;	
	}

}
