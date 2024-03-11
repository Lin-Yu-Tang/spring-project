package com.tom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.tom.filter.AuthenticationLogginFilter;
import com.tom.filter.AuthenticationLoggingFilter;
import com.tom.filter.RequestValidationFilter;
import com.tom.filter.StaticKeyAuthenticationFilter;

@Configuration
public class ProjectConfig {

	private final StaticKeyAuthenticationFilter filter = new StaticKeyAuthenticationFilter();
	
	@Bean
	  public SecurityFilterChain securityFilterChain(HttpSecurity http) 
	    throws Exception {
//	    http.addFilterAt(filter, BasicAuthenticationFilter.class);
	    
	    http.addFilterAfter(new AuthenticationLogginFilter(), BasicAuthenticationFilter.class);
	    http.authorizeHttpRequests(c -> c.anyRequest().permitAll());
	           
	 
	    return http.build();
	  }
	
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//		http.addFilterBefore(new RequestValidationFilter(), BasicAuthenticationFilter.class);
//
//		http.addFilterAfter(new AuthenticationLoggingFilter(), BasicAuthenticationFilter.class);
//
//		http.authorizeHttpRequests(c -> c.anyRequest().permitAll());
//
//		return http.build();
//	}
}