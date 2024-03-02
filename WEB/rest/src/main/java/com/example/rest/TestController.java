package com.example.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
		
	@GetMapping("/p")
	private Product getProdcut() {
		
		Product product = new Product();
		
		product.setId(1);
		product.setName("Apple");
		product.setPrice(111);
		
		return product;
	}
}
