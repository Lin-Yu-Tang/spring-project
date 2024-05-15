package com.tom.service;

import org.springframework.stereotype.Service;

@Service
public class EveningServiceImpl implements GreetingService {

	@Override
	public String getGreeting() {
		return "good evening!!";
	}

}
