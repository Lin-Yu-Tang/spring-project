package com.tom.repository;

import org.springframework.session.Session;
import org.springframework.session.SessionRepository;

public class SessionRepositoryImpl<S extends Session> {

	
	private SessionRepository<S> repository;
	
	
	public void demo() {
		repository.save(null);
		
	}
}
