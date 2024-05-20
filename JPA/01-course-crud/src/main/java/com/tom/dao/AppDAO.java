package com.tom.dao;

import java.util.List;

import com.tom.entity.Course;
import com.tom.entity.Instructor;
import com.tom.entity.Student;

public interface AppDAO {
	
	void save(Instructor instructor);
	
	Instructor findInstructorById(int theId);
	
	void deleteInstructorById(int theId);
	
	void deleteInstructorDetailById(int theId);
	
	void deleteInstructorDetailByIdNotCascadeDelete(int theId);
	
	List<Course> findCoursesByInstructorId(int theId);
	
	int testCountSQL();
	
	Instructor findInstructorByIdJoinFetch(int theId);
	
	void update(Instructor instructor);
	
	void update(Course course);
	
	Course findCourseById(int theId);
	
	void deleteCourseById(int theId);
	
	void save(Course course);
	
	Course findCourseAndReviewByCourseId(int theId);
	
	Course findCourseAndStudentByCourseId(int theId);
	
	Student findStudentAndCourseByStudentId(int theId);
	
	void update(Student student);
	
	void deleteStdentById(int theId);
	
}
