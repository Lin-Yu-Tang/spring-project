package com.tom.service;

import java.util.List;

import com.tom.entity.Student;

public interface InitDataService {

	void createInstructor();
	
	void createStudents();
	
	void createCourseAndStudent(int courseId, List<Student> students);
	
	void createCoursesAndStudents();
}
