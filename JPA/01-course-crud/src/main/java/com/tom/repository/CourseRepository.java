package com.tom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Meta;
import org.springframework.stereotype.Repository;

import com.tom.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
	
	List<Course> findByInstructorLastName(String lastName);
	
	@Meta(comment = "count roles for a given course name")
	long countByTitle(String title);
}
