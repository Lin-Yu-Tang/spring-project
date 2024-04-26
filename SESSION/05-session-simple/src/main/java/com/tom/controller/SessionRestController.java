package com.tom.controller;

import java.util.HashMap;
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
	
	@GetMapping("hello")
	public String test() {
		return "hello";
	}
	
	
	@PostMapping("login")
    public String login(@RequestBody LoginDTO body, HttpServletRequest request) throws Exception {
        if (true) {
            UserInfoVO userInfo = new UserInfoVO();
            userInfo.setUserId(body.getUserId());
            userInfo.setUsername(body.getUsername());
            userInfo.setOrganization(body.getOrganization());
            userInfo.setLocale(body.getLocale());
            
            HttpSession session = request.getSession();
            String id = session.getId();
            System.out.println("session id: " + id);
            session.setAttribute("USER_INFO", userInfo);
        }
        return "";
    }
	
	
	@GetMapping("basic")
	public UserInfoVO getSession(HttpServletRequest request) {
		
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		
		UserInfoVO attribute = (UserInfoVO)requestAttributes.getAttribute("USER_INFO", 0);
		String sessionId = requestAttributes.getSessionId();
		
		UserInfoVO userInfo = (UserInfoVO)request
								.getSession()
								.getAttribute("USER_INFO");
		System.out.println(userInfo);
		if (userInfo == null) {
			return new UserInfoVO();
		}
		
		return userInfo;
	}
	
	/**
	 * session 失效
	 * @param request
	 * @return
	 */
	@PostMapping("/destorySession")
	public String destroySession(HttpServletRequest request) {
		request.getSession().invalidate();
		return "";
	}
	

	
}








