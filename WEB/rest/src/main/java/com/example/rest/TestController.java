package com.example.rest;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
	
	
	@GetMapping("download")
	public void manualDownload(@PathVariable("fileName") String fileName, HttpServletResponse response) 
			throws IOException {
		String rootPath = "";
		
		File file = Paths.get(rootPath + "/" + fileName).normalize().toFile();
		try (BufferedInputStream is = new BufferedInputStream(new FileInputStream(file.getCanonicalPath()));
		        OutputStream os = response.getOutputStream()) {
			response.setHeader("Content-Disposition", "attachment; filename=\""
			        + UriUtils.encode(file.getName().toString(), "UTF-8") + "\"");
			IOUtils.copyLarge(is, os);
		} catch (IOException e) {
//			log.error("找不到指定檔案: {" + file.getName() + "}", e);
			throw e;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
