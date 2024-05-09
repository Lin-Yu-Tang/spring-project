package com.tom.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class HelloInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("::::::::HelloInterceptor - preHandle ::::::::");
		System.out.println("::::::::HelloInterceptor - preHandle ::::::::");
		
		return true;
	}

	/**
	 * 
	 
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 在request完成以後
//		System.out.println("::::::::HelloInterceptor - postHandle ::::::::");
		
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 在postHandle 以後
//		System.out.println("::::::::HelloInterceptor - afterCompletion ::::::::");
		
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	
	
	*/
	

}
