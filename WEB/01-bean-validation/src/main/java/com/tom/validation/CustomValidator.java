package com.tom.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomValidator implements ConstraintValidator<CustomValidate, Object> {

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		return "helloworld".equals(value.toString());
	}

}
