package com.tom.service;

import org.springframework.http.ResponseEntity;

import com.tom.security.dto.LoginResponse;
import com.tom.security.dto.LoginResquest;
import com.tom.security.dto.UserProfile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UserServiceImpl implements UserService {

	@Override
	public ResponseEntity<LoginResponse> login(LoginResquest loginRequest, String accessToken, String refreshToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<LoginResponse> refresh(String accessToken, String refreshToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserProfile getUserProfile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		// TODO Auto-generated method stub
		return null;
	}

}
