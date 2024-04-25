package com.tom.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.tom.bean.UserInfo;


public abstract class BaseController {
	
	@Autowired
    private UserInfo userInfo;

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

}
