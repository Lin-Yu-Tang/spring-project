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
//@Order(1)
@Slf4j
public class HelloFilter2 extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers", "applicationid, Origin, X-Requested-With, Content-Type, Accept");
//        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
		
		log.info("::::::::HelloFilter2::::::::");
		System.out.println("::::::::HelloFilter2::::::::");
		
		String header = request.getHeader("applicationId");
		System.out.println(header);
		

		filterChain.doFilter(request, response);
	}

}
