package com.tom.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/resource")
	public Map<String, Object> home() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", UUID.randomUUID().toString());
		model.put("content", "Hello World");
		return model;
	}
	
	@GetMapping("/currentUser")
	public String user(Principal principal) {
		return currentUser(principal);
	}
	
	@ModelAttribute("currentUserName")
	String currentUser(Principal principal) {
		return (principal != null) ? principal.getName() : null;
	}
}
