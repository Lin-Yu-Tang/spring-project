package com.tom;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.tom.filter.AdminValidationFilter;

//@Configuration
//@Order(1)
public class AdminSecurityConfig {

//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//		http.addFilterBefore(new AdminValidationFilter(), BasicAuthenticationFilter.class);
//
////		http.addFilterAfter(new AuthenticationLoggingFilter(), BasicAuthenticationFilter.class);
//
//		http.authorizeHttpRequests(c -> c.anyRequest().permitAll());
//
//		return http.build();
//	}
//	
//	@Bean
//	UserDetailsService userDetailsService() {
//		UserDetails u1 = User.withUsername("tom").password("12345").roles("admin").build();
//		
//		List<UserDetails> users = List.of(u1);
//		return new InMemoryUserDetailsManager(users);
//	}
//	
//	@Bean
//	PasswordEncoder passwordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}
	
	
}
