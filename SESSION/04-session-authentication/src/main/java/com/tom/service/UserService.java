package com.tom.service;

import org.springframework.http.ResponseEntity;

import com.tom.security.dto.LoginResponse;
import com.tom.security.dto.LoginResquest;
import com.tom.security.dto.UserProfile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public interface UserService {
    ResponseEntity<LoginResponse> login(LoginResquest loginRequest, String accessToken, String refreshToken);

    ResponseEntity<LoginResponse> refresh(String accessToken, String refreshToken);

    UserProfile getUserProfile();
    String logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);
}
