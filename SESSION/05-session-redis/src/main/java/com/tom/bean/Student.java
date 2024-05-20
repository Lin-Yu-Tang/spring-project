package com.tom.bean;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RedisHash("Student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

	public enum Gender {
		MALE, FEMALE
	}
	
	private String id;
	private String name;
	private Gender gender;
	private int grade;
	@TimeToLive
	private Long expired;
	
	public Student(String id, String name, Gender gender, int grade) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.grade = grade;
	}
	

}
