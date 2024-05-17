package com.tom.filter;

import java.lang.reflect.Method;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class HelloInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
//		request.setAttribute("startTime", System.currentTimeMillis());
		
		log.info("::::::::HelloInterceptor - preHandle ::::::::");
		System.out.println("::::::::HelloInterceptor - preHandle ::::::::");
		
		String greeting = request.getHeader("greeting");
		String ip = request.getRemoteAddr();
		 if (handler instanceof ResourceHttpRequestHandler) {
	            System.out.println("preHandle");
		 } else if (handler instanceof HandlerMethod) {
	            HandlerMethod handlerMethod = (HandlerMethod) handler;
	            Method method = handlerMethod.getMethod();
	            System.out.println("IP: " + ip + ", Class: " + 
	            			method.getDeclaringClass().getSimpleName() + ", Method: " + 
	            			method.getName());
		 }

		
		if ("hello world".equals(greeting)) {
			
			// return false break out interceptor
			return false;
		}
		
		return true;
	}

	/**
	 * 
	 */
	 
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 在request完成以後
		System.out.println("::::::::HelloInterceptor - postHandle ::::::::");
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
		
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 在postHandle 以後
		System.out.println("::::::::HelloInterceptor - afterCompletion ::::::::");
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	
	
	

}
