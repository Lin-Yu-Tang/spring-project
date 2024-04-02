package com.tom.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tom.entity.APIResponse;
import com.tom.exception.ServiceException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalDefaultExceptionHandler {

    public static final int DEFAULT_ERROR = 500;

    /**
     * 針對ServiceException單獨處理錯誤訊息。
     * 返回處理錯誤訊息結果。
     *
     * @param ex
     * @return
     * @throws ServiceException
     */
    @ExceptionHandler
    public ResponseEntity<Object> handleServiceException(ServiceException ex) throws ServiceException {
        if (AnnotationUtils.findAnnotation
                (ex.getClass(), ResponseStatus.class) != null)
            throw ex;

        String message = "Exception: " + ex.getClass().getName() + ": " + ex.getMessage();
        APIResponse res = new APIResponse();

        res.setStatusCode(ex.getServiceCode().getValue());
        res.setMessage(message);
        log.error(message, ex);

        return ResponseEntity.ok(res);
    }
    
    
    // TODO
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex) {
    	test(ex);
    	
    	List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
    	return ResponseEntity.ok(getErrorsMap(errors));
    }

    private void test(MethodArgumentNotValidException ex) {
    	BindingResult bindingResult = ex.getBindingResult();
    	FieldError fieldError = ex.getFieldError();
    	List<ObjectError> errors = ex.getAllErrors();
    	
    	errors.forEach(e -> {
    		Object[] arg = e.getArguments();
    		for (int i=0; i < arg.length; i++) {
    			System.out.println("index: "+ i + ", " + arg[i]);
    		}
    	});
	}



	private Map<String, Object> getErrorsMap(List<String> errors) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("statusCode", 1111);
        errorResponse.put("errors", errors);
        return errorResponse;
    }
    
    /**
     * 出現未單獨處理的例外錯誤時,針對錯誤進行處理。
     * 返回處理錯誤訊息結果。
     *
     * @param ex
     * @return
     * @throws Throwable
     */
    @ExceptionHandler
    public ResponseEntity<Object> handleThrowable(Throwable ex) throws Throwable {
        if (AnnotationUtils.findAnnotation
                (ex.getClass(), ResponseStatus.class) != null)
            throw ex;

        String message = "Exception: " + ex.getClass().getName() + ": " + ex.getMessage();
        APIResponse res = new APIResponse();

        res.setStatusCode(DEFAULT_ERROR);
        res.setMessage(message);
        log.error(message, ex);

        return ResponseEntity.ok(res);
    }

}