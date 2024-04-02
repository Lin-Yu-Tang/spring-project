package com.tom.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tom.config.GlobalDefaultExceptionHandler;
import com.tom.entity.APIResponse;
import com.tom.entity.Product;
import com.tom.entity.ProductDTO;
import com.tom.exception.ServiceCode;
import com.tom.repository.ProductRepository;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private ProductRepository repository;
	
	
	@GetMapping("product")
	public List<Product> getAll() {
		return repository.findAll();
	}
	
	@PostMapping("add")
	public ResponseEntity<?> addOne(@RequestBody @Valid ProductDTO body) {
		System.out.println(body);
		return ResponseEntity.ok(new HashMap<>()); 
	}
	
	@PostMapping("test")
    public ResponseEntity<APIResponse> saveStaff() {
    	String arg = "2018-05-05d";
    	Date date = parseStringDateToDate(arg);
    	String stringDate = parseDateToStringDate(new Date(), "yyyy-MM-dd");
    	System.out.println("str" + stringDate);
    	
        APIResponse apiResponse = new APIResponse();
        apiResponse.setStatusCode(ServiceCode.OK.getValue());
        apiResponse.setRsData(new HashMap<>());
        return ResponseEntity.ok(apiResponse);
    }
    
    private Date parseStringDateToDate(String stringDate) {
    	Date date = null;
    	try {
	    	LocalDate localDate = LocalDate.parse(stringDate);
	    	date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    	} catch (DateTimeParseException e) {
    		log.debug("日期轉換錯誤: " + stringDate + " 不可轉換為日期格式。");
    	}
    	return date;
    }
    
    private String parseDateToStringDate(Date date, String pattern) {
    	DateTimeFormatter formatter = null;
    	try {
    		formatter = DateTimeFormatter.ofPattern(pattern);
    	}catch (IllegalArgumentException e) {
    		log.info("日期轉換錯誤: " + pattern + " 不是正確日期格式。");
    	}
    	if (Objects.isNull(formatter)) {
    		return "";
    	}
    	return formatter.format(date
    						.toInstant()
    						.atZone(ZoneId.systemDefault())
			    		    .toLocalDate());
    }
	
}