package com.tom.dto;

public record StudentSearchRequest(
		String firstName, 
		String lastName, 
		String email,
		Integer pageIndex,
		Integer pageSize) {

}
