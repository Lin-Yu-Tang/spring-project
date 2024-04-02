package com.tom;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@ToString
@SessionScope
public class UserInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long cifNo;
	private String username;

	
	@PostConstruct
	public void init() {
		System.out.println("userInfo init");
	}
	
	@PreDestroy
	public void end() {
		System.out.println("userInfo destroy");
	}
	
	
	
}
