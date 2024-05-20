package com.tom.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
public class WebSecurityConfig {

	@Autowired
	private MyUserDetails jwtUserDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

//	private final AuthenticationProvider authenticationProvider;
//	
//	public WebSecurityConfig(AuthenticationProvider authenticationProvider) {
//		this.authenticationProvider = authenticationProvider;
//	}
	
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//
//		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
//	}

//	@Bean
//	public JwtAuthenticationEntryPoint jwtAuthenticationEntryPointBean() throws Exception {
//		return new JwtAuthenticationEntryPoint();
//	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	private void decode() {
		CharSequence rawPwd = null;
		String encodePwd = null;
		passwordEncoder().matches(rawPwd, encodePwd);
	}

	@Bean
	SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable());
		http.authorizeHttpRequests(c -> c
				.requestMatchers("/auth/*").permitAll()
				.anyRequest().authenticated());
		http.formLogin(f -> f.disable());
		http.httpBasic(b -> b.disable());
		
//		http.authenticationProvider(CustomAuthenticationProvider.class);
		
		http.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));
//        .antMatchers("/auth/**").permitAll()
//        .anyRequest().authenticated().and()
//        .formLogin().disable().httpBasic().disable()
//        .sessionManagement()
//        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//        .maximumSessions(1)
//        .maxSessionsPreventsLogin(true)
//        .sessionRegistry(sessionRegistry()).and()
//        .sessionFixation().migrateSession();
//		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}


//	@Bean
//	SessionRegistry sessionRegistry() {
//		SessionRegistry sessionRegistry = new SessionRegistryImpl();
//		return sessionRegistry;
//	}
//
//	@Bean
//	HttpSessionEventPublisher httpSessionEventPublisher() {
//		return new HttpSessionEventPublisher();
//	}

}
