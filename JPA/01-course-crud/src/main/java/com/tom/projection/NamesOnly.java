package com.tom.projection;

public interface NamesOnly {

	String getFirstName();

	String getLastName();
	
	default StudentVO getStudentVO() {
		StudentVO studentVO = new StudentVO(getFirstName(), getLastName());
	    return studentVO;
	  }
}
