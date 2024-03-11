package com.tom;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class TestService {

	
	
	public void buildUser() {
		UserDetails userDetails = User.withUsername("bill")
							        .password("12345")
							        .authorities("read", "write")
							        .accountExpired(false)
							        .disabled(true)
							        .build();
		
		User.UserBuilder builder1 = User.withUsername("bill");
		 
		UserDetails u1 = builder1
		                 .password("12345")
		                 .authorities("read", "write")
		                 .passwordEncoder(p -> encode(p))
		                 .accountExpired(false)
		                 .disabled(true)
		                 .build();
	}

	private String encode(String p) {
		return "";
	}
}
