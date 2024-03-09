package com.tom.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tom.entity.Product;
import com.tom.entity.ProductDTO;
import com.tom.repository.ProductRepository;

import jakarta.validation.Valid;

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
	
	
}