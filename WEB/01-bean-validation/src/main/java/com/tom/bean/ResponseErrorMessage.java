package com.tom.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseErrorMessage {
	
	private String fieldName;
	
	private String message;
	
	private String code;
	
}
