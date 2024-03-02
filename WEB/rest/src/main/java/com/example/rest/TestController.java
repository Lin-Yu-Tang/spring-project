package com.example.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
	
	
	@GetMapping("/")
	public Product getProduct() {
		
		Product product = new Product();
		
		
		return product;
	}
	
	
}
