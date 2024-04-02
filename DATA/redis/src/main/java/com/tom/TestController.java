package com.tom;

import java.net.URL;

import javax.annotation.Resource;
import javax.annotation.Resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {
	
//	// inject the actual template
//    @Autowired
//    private RedisTemplate<String, String> template;
//
//    // inject the template as ListOperations
//    // can also inject as Value, Set, ZSet, and HashOperations
//    @Resource(name="redisTemplate")
//    private ListOperations<String, String> listOps;
//
//    public void addLinkE(String userId, String url) {
//        listOps.leftPush(userId, url);
//        // or use template directly
//        template.boundListOps(userId).leftPush(url);
//    }
//	
//	
//	@Autowired
//	private RedisOperations<String, String> operations;
//	@Autowired
//	private StringRedisTemplate redisTemplate;
//
//	private void addLinkS(String userId, String url) {
//		redisTemplate.opsForList().leftPush(userId, url);
//	}
//	
//	public void addLink(String userId, URL url) {
//	    listOps.leftPush(userId, url.toExternalForm());
//	}
//	
//	
//	
//	@GetMapping("test")
//	public String setValue() {
//		
////		addLinkS("tom", "127.0.0.1");
//		
//		
//		addLinkE("tom", "127.0.0.1");
//		
//		return "";
//	}
}
