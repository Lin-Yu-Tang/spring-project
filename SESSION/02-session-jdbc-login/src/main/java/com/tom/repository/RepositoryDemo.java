package com.tom.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;

public class RepositoryDemo <S extends Session> {

	@Autowired
	private SessionRepository<S> repository; 

//	public void demo() {
//		S toSave = this.repository.createSession(); 

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
//	}
}
