package com.example.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	private Logger log = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);
	
	@ExceptionHandler(value=Exception.class)
	protected ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {
		        String bodyOfResponse = "Exception: " + ex.getClass().getName() +", message: " + ex.getMessage();
		        APIResponse res = new APIResponse();
		        
		        res.setE(ex);
		        res.setMessage(bodyOfResponse);
		        res.setStatus("error");
//		        ex.printStackTrace();
		        logger.error(bodyOfResponse, ex);
		        
		        return handleExceptionInternal(ex, res, 
		          new HttpHeaders(), HttpStatus.OK, request);
	}
	
	
}
