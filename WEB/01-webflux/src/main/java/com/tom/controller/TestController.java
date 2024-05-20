package com.tom.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tom.bean.E2EEPwdGenResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TestController {

	
	@PostMapping("hello")
	public String hello(@RequestBody String body) {
		
		System.out.println(body);
		E2EEPwdGenResponse sendResult = (E2EEPwdGenResponse) transToObject(body,
                E2EEPwdGenResponse.class);
		
		
		System.out.println(sendResult);
		return "";
	}
	
	
	public static <R> Object transToObject(String jsonStr, Class<R> className) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            R r = mapper.readValue(jsonStr, className);
            log.info("mapper result: {}", r.toString());
            return mapper.readValue(jsonStr, className);
        } catch (JsonProcessingException e) {
            log.error("Error: {} ", e.getMessage());
            return null;
        }
    }
}
