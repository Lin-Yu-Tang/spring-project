package com.tom.bean;

import lombok.Data;

@Data
public class LoginResponse {
	
    private String accessToken;
    private String refreshToken;
    
}