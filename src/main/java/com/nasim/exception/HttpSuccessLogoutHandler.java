package com.nasim.exception;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

public class HttpSuccessLogoutHandler implements LogoutSuccessHandler {
	private final HttpStatus httpReturn;

	public HttpSuccessLogoutHandler(HttpStatus httpReturn) {
		super();
		this.httpReturn = httpReturn;
	}

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		response.setStatus(httpReturn.value());
		response.getWriter().flush();
	}

}
