package com.tom;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.ldap.DefaultLdapUsernameToDnMapper;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.userdetails.LdapUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import com.tom.entity.User;
import com.tom.service.InMemoryUserDetailsService;

@Order(2)
@Configuration
public class ProjectConfig {

//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails u = new User("john", "12345", "read");
//		List<UserDetails> users = List.of(u);
//		return new InMemoryUserDetailsService(users);
//	}
	


	@Bean
	public UserDetailsService userDetailsService(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

//	@Bean
//	public PasswordEncoder passwordEncoder2() {
//		Map<String, PasswordEncoder> encoders = new HashMap<>();
//
//		encoders.put("noop", NoOpPasswordEncoder.getInstance());
//		encoders.put("bcrypt", new BCryptPasswordEncoder());
//		encoders.put("scrypt", new SCryptPasswordEncoder(0, 0, 0, 0, 0));
//
//		return new DelegatingPasswordEncoder("bcrypt", encoders);
//	}
}
