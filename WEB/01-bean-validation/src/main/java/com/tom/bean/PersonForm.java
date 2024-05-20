package com.tom.bean;

import com.tom.validation.CustomValidate;
import com.tom.validation.Secret;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PersonForm {
	
	@NotNull
	@Size(max=64)
	@NotBlank
	private String name;

	@Min(0)
	private int age;
	
	@Email
	@NotNull
	private String email;

	@NotNull
	@Pattern(regexp = "[0-9]*")
	private String cid;
	
	@Secret
	private String secret;
	
	@CustomValidate
	private String greeting;
	
}
