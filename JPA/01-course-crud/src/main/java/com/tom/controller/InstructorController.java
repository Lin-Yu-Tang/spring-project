package com.tom.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tom.entity.Instructor;
import com.tom.projection.NameOnly;
import com.tom.repository.InstructorRepository;

@RestController
@RequestMapping("/instructor")
public class InstructorController {
	
	@Autowired
	private InstructorRepository instructorRepository;
	
	@GetMapping("all")
	public List<Instructor> findAll() {
		List<Instructor> all = instructorRepository.findAll();
		
		/**
		 * OneToOne default fetch type are Eager, will query all instructorDetail:
		 * 
		 * select id1_0.id,id1_0.hobby,i1_0.id,i1_0.email,i1_0.first_name,i1_0.last_name,id1_0.youtube_channel 
		   from instructor_detail id1_0 
		   left join instructor i1_0 on id1_0.id=i1_0.instructor_detail_id where id1_0.id=?
		 * 
		 */
		
		return null;
	}
	@GetMapping("alld")
	public List<Instructor> findAllAndGetDetails() {
		List<Instructor> all = instructorRepository.findAll();
		all.stream().forEach(e -> System.out.println(e.getInstructorDetail()));
		/**
		 * OneToOne when fetch type is Lazy, query details when it really used.
		 */
		
		return null;
	}
	
	@GetMapping("course/{title}")
	public List<Instructor> findByCourseTitle(@PathVariable("title") String title) {
		
		// OneToMany no N+1 problem
		
		/**
		 *  
		 *  select i1_0.id,i1_0.email,i1_0.first_name,i1_0.instructor_detail_id,i1_0.last_name 
			from instructor i1_0 left join course c1_0 on i1_0.id=c1_0.instructor_id where c1_0.title=?
			
			### if Eager fetch, additional queries will be executed:
			select c1_0.instructor_id,c1_0.id,c1_0.title from course c1_0 where c1_0.instructor_id=?
		 */
		
		List<Instructor> instructors = instructorRepository.findByCoursesTitle(title);
		
		return null;
		
	}
	
	@GetMapping("lastName/{lastName}")
	public List<Instructor> findByLastName(@PathVariable("lastName") String lastName) {
		
		Collection<NameOnly> instructor = instructorRepository.findByLastName(lastName);
		
		instructor.forEach(System.out::println);
		
		return null;
	}

}
