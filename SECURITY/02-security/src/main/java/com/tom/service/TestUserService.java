package com.tom.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class TestUserService {

	public void createUser() {
		UserDetails u = User.withUsername("bill")
                .password("12345")
                .authorities("read", "write")
                .accountExpired(false)
                .disabled(true)
                .build();
	}
	
	public void createBuilderUser() {
		User.UserBuilder builder1 = User.withUsername("bill");
		 
		UserDetails u1 = builder1
		                 .password("12345")
		                 .authorities("read", "write")
		                 .passwordEncoder(p -> encode(p))
		                 .accountExpired(false)
		                 .disabled(true)
		                 .build();
		 
		User.UserBuilder builder2 = User.withUserDetails(u1);
		 
		UserDetails u2 = builder2.build();
	}

	private String encode(String p) {
		return null;
	}
	
}
