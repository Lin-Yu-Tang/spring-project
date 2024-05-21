package com.tom.entity;

import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Course.class)
public class Course_ {

	public static volatile SingularAttribute<Course, Integer> id;
	
	public static volatile SingularAttribute<Course, String> title;
	
	public static volatile SingularAttribute<Course, Instructor> instructor;
	
	public static volatile ListAttribute<Course, Review> reviews;
	
	public static volatile SingularAttribute<Course, Student> students;
	
}
