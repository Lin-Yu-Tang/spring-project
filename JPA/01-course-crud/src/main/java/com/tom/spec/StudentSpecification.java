package com.tom.spec;

import org.springframework.data.jpa.domain.Specification;

import com.tom.entity.Student;
import com.tom.entity.Student_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class StudentSpecification {
	
	public static Specification<Student> equalFirstName(final String firstName) {
        return new Specification<Student>() {
			public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            	return cb.equal(root.get(Student_.firstName), firstName);
            }
        };
    }
	
	public static Specification<Student> equalLastName(final String lastName) {
        return new Specification<Student>() {
			public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            	return cb.equal(root.get(Student_.lastName), lastName);
            }
        };
    }
	
	public static Specification<Student> likeFirstName(final String firstName) {
        return new Specification<Student>() {
			public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            	return cb.or(cb.like(root.get(Student_.firstName), "%" + firstName + "%"));
            }
        };
    }
	
	
}
