package com.tom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jedis")
public class JedisController {

	
	@Autowired
	private StudentRepository studentRepository;
	
	@GetMapping("save")
	public void saveStudent() {
		
		Student student = new Student("s002", "John Doe", Student.Gender.MALE, 1);
		student.setExpired(60L);
		
		studentRepository.save(student);
	}
	
	@GetMapping("get")
	public void getStudent() {
		Student retrievedStudent = 
				  studentRepository.findById("Eng2015001").get();
		
		System.out.println(retrievedStudent);
	}
	
}
