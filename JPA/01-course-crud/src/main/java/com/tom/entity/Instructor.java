package com.tom.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@EnableJpaAuditing
@Table(name="instructor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Instructor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;

//	@OneToOne(cascade = CascadeType.ALL)
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "instructor_detail_id")
	private InstructorDetail instructorDetail;
	
//	@OneToMany(fetch = FetchType.EAGER, mappedBy = "instructor", cascade = CascadeType.ALL)
	@OneToMany(mappedBy = "instructor", 
			fetch = FetchType.LAZY,
			cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private List<Course> courses;

	public Instructor(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public void add(Course tmpCourse) {
		if (courses == null) {
			courses = new ArrayList<>();
		}
		courses.add(tmpCourse);
		
		tmpCourse.setInstructor(this);
	}

}
