package com.tom.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.tom.bean.PersonForm;

import jakarta.validation.Valid;

@Service
@Validated
public class HelloServiceImpl implements HelloService {

	@Override
	public void helloworld(@Valid PersonForm personForm) {
		
		// validation is not work
		
		System.out.println("hello world!!");
	}
	
}
