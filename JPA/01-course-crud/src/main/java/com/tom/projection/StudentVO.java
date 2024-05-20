package com.tom.projection;


public class StudentVO {

	private String firstName;
	
	private String lastName;

	public StudentVO(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "StudentVO [firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
}
