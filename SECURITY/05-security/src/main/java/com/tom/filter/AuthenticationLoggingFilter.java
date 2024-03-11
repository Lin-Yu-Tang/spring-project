package com.tom.filter;

import java.io.IOException;

import org.slf4j.Logger;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenticationLoggingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {

		var httpRequest = (HttpServletRequest) request;

		var requestId = httpRequest.getHeader("Request-Id");
		System.out.println("after filter");

		log.info("Successfully authenticated request with id " + requestId);

		filterChain.doFilter(request, response);
	}
}