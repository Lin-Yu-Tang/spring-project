package com.tom.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class APIResponse {

	private int statusCode;
	private String message;
	private Object rsData;
	
}