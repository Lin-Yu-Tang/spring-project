package com.tom.jpatest.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

public interface AppDAO {
	
	List executeQuery(String queryString);
	
	List findByPage(int page);

	List findByPage(Pageable pageable);
}
