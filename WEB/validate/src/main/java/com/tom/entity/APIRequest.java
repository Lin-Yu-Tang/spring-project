package com.tom.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class APIRequest {

	@NotNull
	private String baseInfo;
	
	
}