package com.tom.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserInfoVO implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
	private String userId;
    private String username;
    private String organization;
    private String locale;

}
