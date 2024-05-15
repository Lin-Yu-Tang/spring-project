package com.tom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tom.service.GreetingService;

@RestController
public class HelloController {
	
	@Autowired
//	@Qualifier(value = "morningServiceImpl")
	@Qualifier(value = "eveningServiceImpl")
	private GreetingService service;
	
	@GetMapping("hello")
	public String hello() {
		return service.getGreeting();
	}
	
	@GetMapping("el")
	public String el() {
		ExpressionParser parser = new SpelExpressionParser();
		
		return "";
	}

}
