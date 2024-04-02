package com.tom;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.tom.entity.User;
import com.tom.serivce.InMemoryUserDetailsService;


@Configuration
public class SecurityConfig {

//	@Bean
//	SecurityFilterChain configure(HttpSecurity http) throws Exception {
//		http.httpBasic(Customizer.withDefaults());
//		http.authorizeHttpRequests(c -> c.anyRequest().permitAll());
//		http.csrf(csrf -> csrf.disable());
//		
//		return http.build();
//	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails u1 = new User("john", "12345", "read");
		UserDetails u2 = new User("mary", "12345", "read");
		
		List<UserDetails> users = List.of(u1,u2);
		return new InMemoryUserDetailsService(users);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
