package com.tom.bean;

import java.util.List;

import org.springframework.data.domain.Pageable;

public interface CursorPage<T> {

	List<T> getContent();
	
	Pageable getPageable();
	
	long getTotal();
}
