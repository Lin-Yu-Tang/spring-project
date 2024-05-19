package com.tom.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tom.entity.Instructor;
import com.tom.projection.NameOnly;
import com.tom.projection.NamesOnly;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Integer> {

	List<Instructor> findByCoursesTitle(String title);
	
	Collection<NameOnly> findByLastName(String lastname);
	
}
