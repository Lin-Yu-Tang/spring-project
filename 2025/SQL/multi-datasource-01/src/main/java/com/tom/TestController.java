package com.tom;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@Autowired
	private MultiDatasourceService service;
	
	@GetMapping("test")
	public String test() throws Exception {
//		service.testMultiDataSource();
		service.testTransaction();
		return "OK";
	}
	
	
	@GetMapping("hello")
	public String hello() throws Exception {
		return "hello world";
	}
	
	@PostMapping("/hello")
    public String hello(@RequestBody Map<String, String> payload) {
        // 從請求體中提取數據
        String name = payload.getOrDefault("name", "guest");
        String greeting = payload.getOrDefault("greeting", "hello");
        return String.format("%s, %s!", greeting, name);
    }
	
}
