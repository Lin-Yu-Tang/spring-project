package com.tom;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisStandaloneConfiguration {

	@Bean
	  public RedisConnectionFactory redisConnectionFactory() {
	    return new LettuceConnectionFactory();
	  }
	
	
//	@Bean
//	public RedisConnectionFactory lettuceConnectionFactory() {
//		return new LettuceConnectionFactory("127.0.0.1", 6379);
//	}
	
//	@Bean
//	LettuceConnectionFactory connectionFactory() {
//	    return new LettuceConnectionFactory();
//	  }

//	 @Bean
//	  RedisTemplate<String, String> redisTemplate(RedisConnectionFactory connectionFactory) {
//
//	    RedisTemplate<String, String> template = new RedisTemplate<>();
//	    template.setConnectionFactory(connectionFactory);
//	    return template;
//	  }

}
