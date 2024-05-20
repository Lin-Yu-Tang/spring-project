package com.tom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tom.bean.PersonForm;
import com.tom.service.HelloService;

import jakarta.validation.Valid;

@RestController
public class HelloController {
	
	@Autowired
	private HelloService helloService;
	
	@PostMapping("hello")
	public String hello(@RequestBody @Valid PersonForm body) {
		
		return "";
	}
	
	@GetMapping("ex1")
	public String ex1() throws Exception {
		throw new Exception();
	}
	
	@PostMapping("helloworld")
	public String helloworld(@RequestBody PersonForm body) {
		
		helloService.helloworld(body);
		
		return "";
	}

}
