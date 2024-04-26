package com.tom.repository;

import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CustomSessionRepository <S extends Session> {

	private SessionRepository<S> repository; 

	public void save() {
		S toSave = this.repository.createSession();
//		S toSave = this.repository
//		User rwinch = new User("rwinch");
//		toSave.setAttribute(ATTR_USER, rwinch);
//
//		this.repository.save(toSave); 
//
//		S session = this.repository.findById(toSave.getId()); 
//
//		
//		User user = session.getAttribute(ATTR_USER);
//		assertThat(user).isEqualTo(rwinch);
	}
}
