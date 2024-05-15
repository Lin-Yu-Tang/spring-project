package com.tom.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tom.bean.UserInfo;
import com.tom.repository.UserInfoRepository;

@RestController
public class HelloController {
	
	@Autowired
	private UserInfoRepository repository;
	
	@PostMapping("add")
	public ResponseEntity<?> add() {
		
        UserInfo userInfo = new UserInfo();
        userInfo.setUserIp("127.0.0.1");
        userInfo.setUserName("tom ");
        userInfo.setCifId("CIF111123333");
        repository.save(userInfo);
		
		return ResponseEntity.ok("");
	}
	
	@GetMapping("get")
	public ResponseEntity<?> get() {
		//	CIF111123333
        Optional<UserInfo> userOpt = repository.findById("CIF111123333");
        
        if (userOpt.isPresent()) {
        	System.out.println(userOpt.get());
        	return ResponseEntity.ok(userOpt.get());
        }
		
		return ResponseEntity.ok("");
	}
	

}















