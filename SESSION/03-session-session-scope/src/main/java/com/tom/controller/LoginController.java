package com.tom.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tom.bean.APIResponse;
import com.tom.bean.LoginDTO;
import com.tom.bean.UserInfo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class LoginController extends BaseController {
	
	@PostMapping("login")
	public ResponseEntity<?> login(HttpServletRequest request, @RequestBody LoginDTO body) throws Exception {
//		setSessionUserInfo();
            
		HttpSession session = request.getSession(true);
		
		UserInfo userInfo = new UserInfo();
		userInfo.setCifId("A10000000");
		userInfo.setUserName("tom");
		
		session.setAttribute("userInfo", userInfo);
            
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
	
	
	private void setSessionUserInfo() {
		UserInfo userInfo = getUserInfo();
		
		userInfo.setCifId("A10000000");
		userInfo.setUserName("tom");
	}
		
	@GetMapping("basic")
    public ResponseEntity<?> getBasicInfo(HttpServletRequest request) throws Exception {
//        UserInfo user = getUserInfo();
//        UserInfo userInfo = new UserInfo();
//        BeanUtils.copyProperties(user, userInfo);
		
		HttpSession session = request.getSession(false);
		
		var userInfo = (UserInfo)session.getAttribute("userInfo");

		
		
		
        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }
	
	

}
