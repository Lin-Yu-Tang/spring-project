package com.tom;

import org.springframework.web.context.annotation.SessionScope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Data;

@Data
@SessionScope
public class SomeSession {
	
	private String name;

	@PostConstruct
	public void init() {
		System.out.println("SomeSession init");
	}
	
	@PreDestroy
	public void end() {
		System.out.println("SomeSession destroy");
	}
}
