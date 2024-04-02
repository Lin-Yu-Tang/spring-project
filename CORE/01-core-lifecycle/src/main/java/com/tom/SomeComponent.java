package com.tom;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class SomeComponent {

	private SomeSession session;
	
	
	@PostConstruct
	public void init() {
		System.out.println("SomeComponent init");
	}
	
	@PreDestroy
	public void end() {
		System.out.println("SomeComponent destroy");
	}
}
