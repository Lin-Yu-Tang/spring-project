package com.tom.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class APIResponse {
	
	private String status = "success";
	private String message;
	private Object rsData;
	
}
