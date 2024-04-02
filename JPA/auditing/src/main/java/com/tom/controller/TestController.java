package com.tom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tom.entity.Product;
import com.tom.entity.ProductDTO;
import com.tom.serivce.ProductService;

@RestController
public class TestController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping("hello")
	public String hello() {
		return "hello";
	}
	
	@GetMapping("all")
	public List<Product> allProduct() {
		List<Product> all = service.findAll();
		return all;
	}
	
	@PostMapping("add")
	public String addProduct(@RequestBody ProductDTO product) {
		
		System.out.println(product);
		service.insertProduct(product);
		return "OK";
	}
	
	@PostMapping("update")
	public String updateProduct(@RequestBody ProductDTO product) {
		
		System.out.println(product);
		service.updateProduct(product);
		return "OK";
	}
}
