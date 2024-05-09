package com.tom.filter;

import java.io.IOException;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
//@Order(2)
@Slf4j
public class HelloFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		log.info("::::::::HelloFilter::::::::");
		System.out.println("::::::::HelloFilter::::::::");
		
		String header = request.getHeader("applicationId");
		System.out.println(header);
		
		
		filterChain.doFilter(request, response);
	}

}
