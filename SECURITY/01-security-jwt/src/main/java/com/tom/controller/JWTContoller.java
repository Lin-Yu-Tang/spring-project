package com.tom.controller;

import org.springframework.web.bind.annotation.RestController;

import com.tom.bean.AppUser;
import com.tom.bean.LoginRequest;
import com.tom.bean.LoginResponse;
import com.tom.service.TokenService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
public class JWTContoller {

	@Autowired
    private TokenService tokenService;

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        System.out.println("username = " + request.getUsername());
        AppUser appUser = new AppUser();
        LoginResponse res = tokenService.createToken(request);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/auth/refresh-token")
    public ResponseEntity<Map<String, String>> refreshAccessToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        String accessToken = tokenService.refreshAccessToken(refreshToken);
        Map<String, String> res = Map.of("accessToken", accessToken);

        return ResponseEntity.ok(res);
    }

    @GetMapping("/parse-token")
    public ResponseEntity<Map<String, Object>> parseToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        System.out.println("auth:\n" + authorization);
        Map<String, Object> jwtPayload = tokenService.parseToken(authorization.replaceFirst("^Bearer ", ""));
        return ResponseEntity.ok(jwtPayload);
    }
	
}
