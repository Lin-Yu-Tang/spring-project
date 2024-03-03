package com.example.rest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class APIResponse {
	
	private String message;
	
	private Exception e;
	
	private String status = "success";
	
}
