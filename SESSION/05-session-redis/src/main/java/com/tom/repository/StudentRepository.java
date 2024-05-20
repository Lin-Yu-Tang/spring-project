package com.tom.repository;

import org.springframework.data.repository.CrudRepository;

import com.tom.bean.Student;

public interface StudentRepository extends CrudRepository<Student, String> {

}