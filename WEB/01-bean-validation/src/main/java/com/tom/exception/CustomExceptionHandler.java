package com.tom.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.tom.bean.APIResponse;
import com.tom.bean.ResponseErrorMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {
	
	public static final String DEFAULT_ERROR = "error";

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> defaultErrorHandler(Exception ex, WebRequest request) throws Exception {
	    if (AnnotationUtils.findAnnotation
	                (ex.getClass(), ResponseStatus.class) != null)
	      throw ex;
		
		String message = "Exception: " + ex.getClass().getName() +": " + ex.getMessage();
        APIResponse res = new APIResponse();
        
        res.setStatus(DEFAULT_ERROR);
        res.setMessage(message);
        log.error(message, ex);
        
		return ResponseEntity.ok(res);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> validationError(MethodArgumentNotValidException ex) {
	    BindingResult result = ex.getBindingResult();
	    final List<FieldError> fieldErrors = result.getFieldErrors();
	    
	    List<ResponseErrorMessage> response = result.getFieldErrors().stream().map(e -> {
	    	return new ResponseErrorMessage(String.join(".", e.getObjectName(), e.getField()), e.getDefaultMessage(), e.getCode());
	    }).collect(Collectors.toList());
	    
	    FieldError[] errors = (FieldError[])fieldErrors.toArray(new FieldError[fieldErrors.size()]);

	    return ResponseEntity.ok(response);
	}
}
