package com.example.rest;

import java.util.Map;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/test")
public class TestController {
		
	@GetMapping("/p")
	private Product getProdcut(@RequestHeader Map<String, String> map) {
		
		Set<String> keySet = map.keySet();
		keySet.forEach(e -> System.out.println(e + ": " + map.get(e)));
		
		
		Product product = new Product();
		
		product.setId(1);
		product.setName("Apple");
		product.setPrice(111);
		
		return product;
	}
	
	@PostMapping("/q")
	private String postTest(@RequestHeader Map<String, String> map) {
		
		Set<String> keySet = map.keySet();
//		keySet.forEach(System.out::println);
		keySet.forEach(e -> System.out.println(e + ": " + map.get(e)));
		
		
		
		
		return "post";
	}
	
	@GetMapping("/test")
	private String test(HttpServletRequest req) {
		
		Map<String, String[]> map = req.getParameterMap();
		
		Set<String> keySet = map.keySet();
		keySet.forEach(e -> System.out.println(e + ": " + map.get(e)));
		
		return "";
	}
	
	
	
	@GetMapping("/ex")
	private String testException() throws Exception {
		
//		throw new IllegalArgumentException();
		
		throw new NullPointerException();
		
//		throw new RuntimeException();
//		throw new Exception();
		
	}
	
	@PostMapping("/ex")
	private String testExceptionPost() throws Exception {
		
//		throw new IllegalArgumentException();
		
//		throw new NullPointerException();
		
//		throw new RuntimeException();
		throw new Exception("hello exception");
		
//		int int1 = Integer.parseInt("一");
		
//		return "";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
