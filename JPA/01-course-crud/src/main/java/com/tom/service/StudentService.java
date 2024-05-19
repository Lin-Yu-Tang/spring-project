package com.tom.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.tom.dto.StudentSearchRequest;
import com.tom.entity.Student;

public interface StudentService {

	Page<Student> findBySpecification(Specification<Student> spec, Pageable pageable);
	
	List<Student> findBySearchCondition(StudentSearchRequest searchRequest);
	
	Page<Student> findPagingBySearchCondition(StudentSearchRequest searchRequest);
	
}
