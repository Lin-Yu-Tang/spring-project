package com.tom;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {

	
	@Bean
	SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.httpBasic(Customizer.withDefaults());

		http.authorizeHttpRequests(c -> c.anyRequest().permitAll());
		http.csrf(csrf -> csrf.disable());
		
		return http.build();
	}
	
	/**
	 * Custom provider
	 * @return
	 */
//	@Bean
//	SecurityFilterChain configure(HttpSecurity http) throws Exception {
//	    http.httpBasic(Customizer.withDefaults());
//	       
//	    http.authenticationProvider(authenticationProvider);
//	 
//	    http.authorizeHttpRequests(
//	      c -> c.anyRequest().authenticated()
//	    );
//	 
//	    return http.build();
//	}

//	@Bean
//	UserDetailsService userDetailsService() {
//		UserDetails user = User.withUsername("john").password("12345").authorities("read").build();
//		return new InMemoryUserDetailsManager(user);
//	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	
}
