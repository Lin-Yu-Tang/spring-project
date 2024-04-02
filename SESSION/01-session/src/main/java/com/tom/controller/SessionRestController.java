package com.tom.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.tom.bean.LoginDTO;
import com.tom.bean.UserInfoVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class SessionRestController {
	
	@PostMapping("loginProcess")
    public String login(@RequestBody LoginDTO body, HttpServletRequest request) throws Exception {
        if (true) {
            UserInfoVO userInfo = new UserInfoVO();
            userInfo.setUserId(body.getUserId());
            userInfo.setUsername(body.getUsername());
            userInfo.setOrganization(body.getOrganization());
            userInfo.setLocale(body.getLocale());
            
            HttpSession session = request.getSession();
            request.getSession().setAttribute("USER_INFO", userInfo);
        }
        return "";
    }
	
	
	@GetMapping("session")
	public UserInfoVO getSession(HttpServletRequest request) {
		
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		
		UserInfoVO attribute = (UserInfoVO)requestAttributes.getAttribute("USER_INFO", 0);
		String sessionId = requestAttributes.getSessionId();
		
		UserInfoVO userInfo = (UserInfoVO)request
								.getSession()
								.getAttribute("USER_INFO");
		System.out.println(userInfo);
		return userInfo;
	}
	
}








