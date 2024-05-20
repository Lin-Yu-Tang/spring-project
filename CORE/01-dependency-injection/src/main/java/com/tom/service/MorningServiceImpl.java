package com.tom.service;

import org.springframework.stereotype.Service;

@Service
public class MorningServiceImpl implements GreetingService {

	@Override
	public String getGreeting() {
		return "good morning!!";
	}

}
