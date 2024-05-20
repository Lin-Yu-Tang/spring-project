package com.tom.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Window;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tom.entity.Student;
import com.tom.projection.NamesOnly;
import com.tom.projection.StudentVO;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>, JpaSpecificationExecutor<Student> {

	List<Student> findByCoursesTitle(String title);
	
	Window<Student> findFirst3ByLastNameOrderByFirstName(String lastName, ScrollPosition position);
	
//	Collection<Student> findByLastName(String lastname);
	
	Collection<NamesOnly> findByLastName(String lastname);
	
//	@Query("SELECT s.firstName AS firstName, s.lastName AS lastName FROM Student s")
	Page<Student> findByLastName(String lastname, Pageable pageable);
}


