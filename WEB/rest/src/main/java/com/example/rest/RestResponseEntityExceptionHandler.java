package com.example.rest;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	public static final String DEFAULT_ERROR = "error";
	
//	@ExceptionHandler(value=Exception.class)
//	protected ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {
//		        String bodyOfResponse = "Exception: " + ex.getClass().getName() +", message: " + ex.getMessage();
//		        APIResponse res = new APIResponse();
//		        
//		        res.setE(ex);
//		        res.setMessage(bodyOfResponse);
//		        res.setStatus(DEFAULT_ERROR);
//		        log.error(bodyOfResponse, ex);
//		        
//		        return handleExceptionInternal(ex, res, 
//		          new HttpHeaders(), HttpStatus.OK, request);
//	}
	
	@ExceptionHandler(value=Exception.class)
	private ResponseEntity<Object> defaultErrorHandler(Exception ex, WebRequest request) throws Exception {
		// If the exception is annotated with @ResponseStatus rethrow it and let
	    // the framework handle it - like the OrderNotFoundException example
	    // at the start of this post.
	    // AnnotationUtils is a Spring Framework utility class.
	    if (AnnotationUtils.findAnnotation
	                (ex.getClass(), ResponseStatus.class) != null)
	      throw ex;
		
		String message = "Exception: " + ex.getClass().getName() +": " + ex.getMessage();
        APIResponse res = new APIResponse();
        
        res.setStatus(DEFAULT_ERROR);
        res.setMessage(message);
        log.error(message, ex);
        
		return handleExceptionInternal(ex, res, 
		          new HttpHeaders(), HttpStatus.OK, request);
	}
}
