package com.tom.bean;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PersonForm {
	
	@NotNull
	@Size(max=64)
	private String name;

	@Min(0)
	private int age;

}
