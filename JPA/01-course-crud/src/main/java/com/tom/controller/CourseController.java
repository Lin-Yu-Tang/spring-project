package com.tom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tom.entity.Course;
import com.tom.repository.CourseRepository;

@RestController
@RequestMapping("/course")
public class CourseController {
	
	@Autowired
	private CourseRepository courseRepository;
	
	@GetMapping("all")
	public List<Course> findAll() {
		List<Course> all = courseRepository.findAll();
		
		/**
		 * ManyToOne default fetch type is Eanger:
		 * 
		 * select c1_0.id,c1_0.instructor_id,c1_0.title from course c1_0
		 * 
		 * also query:
		 * 
		 * select i1_0.id,i1_0.email,i1_0.first_name,i1_0.instructor_detail_id,i1_0.last_name 
		 * from instructor i1_0 where i1_0.id=?
		 * 
		 * 
		 */
		
		return null;
	}
	
	@GetMapping("instructor/{lastName}")
	public List<Course> findByInstructorLastName(@PathVariable("lastName") String lastName) {
		List<Course> courses = courseRepository.findByInstructorLastName(lastName);
		
		/**
		 * select c1_0.id,c1_0.instructor_id,c1_0.title from course c1_0 
		 * left join instructor i1_0 on i1_0.id=c1_0.instructor_id where i1_0.last_name=?
		 * 
		 * 
		 */
		
		return null;
	}
	
	@GetMapping("countName")
	public String countByName() {
		long countByName = courseRepository.countByTitle("java");
		System.out.println("count: " + countByName);
		
		return "";
	}

}
