package com.tom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.tom.condition.StudentSearchCondition;
import com.tom.dto.StudentSearchRequest;
import com.tom.entity.Student;
import com.tom.repository.StudentRepository;
import com.tom.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public Page<Student> findBySpecification(Specification<Student> spec, Pageable pageable) {
		
		Page<Student> students = studentRepository.findAll(spec, pageable);
		return students;
	}

	@Override
	public List<Student> findBySearchCondition(StudentSearchRequest searchRequest) {
		
		StudentSearchCondition studentSearchCondition = new StudentSearchCondition();
		studentSearchCondition.setFirstName(searchRequest.firstName());
		studentSearchCondition.setLastName(searchRequest.lastName());
		
		return studentRepository.findAll(studentSearchCondition.getSpecification());
	}

	@Override
	public Page<Student> findPagingBySearchCondition(StudentSearchRequest searchRequest) {
		PageRequest page = PageRequest.of(searchRequest.pageIndex() - 1, searchRequest.pageSize());
		
		StudentSearchCondition studentSearchCondition = new StudentSearchCondition();
		studentSearchCondition.setFirstName(searchRequest.firstName());
		studentSearchCondition.setLastName(searchRequest.lastName());
		
		return studentRepository.findAll(studentSearchCondition.getSpecification(), page);
	}
	
	
}
