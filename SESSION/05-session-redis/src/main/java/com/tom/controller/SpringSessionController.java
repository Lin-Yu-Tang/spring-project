package com.tom.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bugsnag.Bugsnag;
import com.tom.bean.LoginDTO;
import com.tom.bean.UserInfoVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("session")
public class SpringSessionController {
	
	@GetMapping("hello")
	public String hello() {
		Bugsnag bugsnag = new Bugsnag("adb72c09d3a8d3a1519cb1c53592adbd");
		bugsnag.notify(new RuntimeException("Test error"));
		return "hello";
	}
	
	@PostMapping("login")
	public String home(@RequestBody LoginDTO body, HttpServletRequest request) {
		UserInfoVO userInfo = new UserInfoVO();
        userInfo.setUserId(body.getUserId());
        userInfo.setUsername(body.getUsername());
        userInfo.setOrganization(body.getOrganization());
        userInfo.setLocale(body.getLocale());
        
        HttpSession session = request.getSession();
        session.setAttribute("USER_INFO", userInfo);
        Bugsnag bugsnag = new Bugsnag("adb72c09d3a8d3a1519cb1c53592adbd");
		bugsnag.notify(new RuntimeException("Test error"));
		
		return "";
	}
	
}
