package com.tom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
		
	@Autowired
	private UserInfo userInfo;
	@Autowired
	private SomeComponent com;
	@Autowired
	private SomeService service;
	
	
	@GetMapping("hello")
	public String test() {
		System.out.println(userInfo);
		System.out.println(com.getSession());
		SomeSession session = com.getSession();
		SomeSession ss = new SomeSession();
		ss.setName("hello");
		com.setSession(ss);
		userInfo.setCifNo(111L);
		userInfo.setUsername("tom");
		return "";
	}
	
	@GetMapping("hi")
	public String getTest() {
		System.out.println(com.getSession());
		System.out.println(userInfo);
		return "";
	}
	
	@GetMapping("serve")
	public String getServe() {
		SomeSession session = service.getSession();
		System.out.println(session);
		return "";
	}
	
	@GetMapping("entity")
	public String getEntity() {
		Product product = new Product();
		return "";
	}
}
