package com.tom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SomeServiceImpl implements SomeService {

	@Autowired
	private SomeComponent com;
	@Override
	public SomeSession getSession() {
		return com.getSession();
	}
	
	
	
}
