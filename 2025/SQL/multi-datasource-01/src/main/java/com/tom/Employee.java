package com.tom;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Employee {

	private long id;
	private String name;
	private String department;
	private double salary;
	private Timestamp joinDate;
}
