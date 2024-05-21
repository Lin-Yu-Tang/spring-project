package com.tom.entity;

import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Instructor.class)
public class Instructor_ {
	
public static volatile SingularAttribute<Student, Integer> id;
	
	public static volatile SingularAttribute<Student, String> firstName;
	
	public static volatile SingularAttribute<Student, String> lastName;
	
	public static volatile SingularAttribute<Student, String> email;
	
	public static volatile SingularAttribute<Student, InstructorDetail> instructorDetail;

	public static volatile ListAttribute<Student, Course> courses;

}
