package com.tom.dto;

public record StudentSearchRequest(
		String firstName, 
		String lastName, 
		String email,
		PageRequest pageRequest) {
}
