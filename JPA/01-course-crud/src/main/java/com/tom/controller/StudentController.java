package com.tom.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.IntFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.OffsetScrollPosition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Window;
import org.springframework.data.support.WindowIterator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tom.dto.StudentSearchRequest;
import com.tom.entity.Course;
import com.tom.entity.Student;
import com.tom.projection.NamesOnly;
import com.tom.projection.StudentVO;
import com.tom.repository.StudentRepository;
import com.tom.service.StudentService;

import jakarta.persistence.FetchType;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private StudentService studentService;

	@GetMapping("all")
	public List<Student> findAll() {

		List<Student> all = studentRepository.findAll();

		/**
		 * select s1_0.id,s1_0.email,s1_0.first_name,s1_0.last_name from student s1_0
		 * 
		 * 
		 * Eager fetch additional query: select
		 * c1_0.student_id,c1_1.id,c1_1.instructor_id,c1_1.title from course_student
		 * c1_0 join course c1_1 on c1_1.id=c1_0.course_id where c1_0.student_id=?
		 * 
		 * 
		 */

		return null;
	}

	@GetMapping("course/{title}")
	public List<Student> findByCourseTitle(@PathVariable("title") String title) {

		List<Student> students = studentRepository.findByCoursesTitle(title);

		/**
		 * 
		 * select s1_0.id,s1_0.email,s1_0.first_name,s1_0.last_name from student s1_0
		 * left join course_student c1_0 on s1_0.id=c1_0.student_id left join course
		 * c1_1 on c1_1.id=c1_0.course_id where c1_1.title=?
		 * 
		 * select c1_0.student_id,c1_1.id,c1_1.instructor_id,c1_1.title from
		 * course_student c1_0 join course c1_1 on c1_1.id=c1_0.course_id where
		 * c1_0.student_id=?
		 * 
		 */

		return null;
	}

	@GetMapping("lastName/{page}")
	public List<Student> find10ByLastName(@PathVariable("page") int page) {

		Window<Student> students = studentRepository.findFirst3ByLastNameOrderByFirstName("robinson",
				ScrollPosition.offset());
		
		System.out.println(students.size());
		
		ScrollPosition positionAt = students.positionAt(page);
		

		
		Window<Student> tempData = studentRepository.findFirst3ByLastNameOrderByFirstName("robinson",
				positionAt);
		
		tempData.forEach(System.out::println);
		
		return null;
	}
	
	@GetMapping("lastName")
	public List<Student> find10ByLastName() {

		Window<Student> students = studentRepository.findFirst3ByLastNameOrderByFirstName("robinson",
				ScrollPosition.offset());
		do {

			for (Student s : students) {
				// consume the user
				System.out.println(s);
			}

			// obtain the next Scroll
			ScrollPosition positionAt = students.positionAt(students.size() - 1);
			students = studentRepository.findFirst3ByLastNameOrderByFirstName("robinson",
					positionAt);
		} while (!students.isEmpty() && students.hasNext());

		
		return null;
	}
	
	@GetMapping("lastName2")
	public String findByLastName2() {
		
		WindowIterator<Student> students = WindowIterator.of(position -> 
			studentRepository.findFirst3ByLastNameOrderByFirstName("robinson", position))
				  .startingAt(ScrollPosition.keyset());
		
		students.forEachRemaining(System.out::println);
		
		return "";
	}
	
	@GetMapping("lastName3/{lastName}")
	public String findByLastName3(@PathVariable("lastName") String lastName) {
		
//		Collection<Student> student = studentRepository.findByLastName(lastName);
//		student.forEach(System.out::println);
		
		
		return "";
	}
	
	@GetMapping("lastName4/{lastName}")
	public String findByLastName4(@PathVariable("lastName") String lastName) {
		
		Collection<NamesOnly> student = studentRepository.findByLastName(lastName);
		student.forEach(e -> System.out.println(e.getFirstName() +"_" + e.getLastName()));
		
		return "";
	}
	
	@GetMapping("lastName5/{lastName}")
	public String findByLastNamePageable(@PathVariable("lastName") String lastName) {
		
		PageRequest request = PageRequest.of(0, 3);
		
		Page<Student> students = studentRepository.findByLastName(lastName, request);
		
		List<Student> content = students.getContent();
		content.forEach(System.out::println);
		
		return "";
	}
	
	@GetMapping("ex/{lastName}")
	public String queryExample(@PathVariable("lastName") String lastName) {
		
		Student student = new Student();
		student.setLastName(lastName);
		ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("id");
		Example<Student> ex = Example.of(student, matcher);
		
		PageRequest request = PageRequest.of(0, 3);
		Page<Student> students = studentRepository.findAll(ex, request);
		
		students.forEach(System.out::println);
		
		return "";
	}
	
	@GetMapping("ex2/{condition}")
	public String queryExample2(@PathVariable("condition") String condition) {
		Course courseExample = new Course();
        courseExample.setTitle("Java");
        
        Student studentExample = new Student();
        studentExample.setCourses(Collections.singletonList(courseExample));
        
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withIgnorePaths("firstName")
                .withIgnorePaths("lastName")
                .withIgnorePaths("email");
		Example<Student> ex = Example.of(studentExample, matcher);
		
		PageRequest request = PageRequest.of(0, 3);
		Page<Student> students = studentRepository.findAll(ex, request);
		
		students.forEach(System.out::println);
		
		return "";
	}
	
	@PostMapping("/search")
	public String searchByCondition(@RequestBody StudentSearchRequest body) {
		
		List<Student> students = studentService.findBySearchCondition(body);
		
		students.stream().forEach(System.out::println);
		
		return "";
	}
	
	
	
	
}
