package com.tom.condition;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.tom.entity.Student;
import com.tom.spec.StudentSpecification;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class StudentSearchCondition implements SearchCondition<Student> {

	private static final long serialVersionUID = 9036230807998070179L;
	
	private String firstName;
	
	private String lastName;
	
	@Override
	public Specification<Student> getSpecification() {
		
		Specification<Student> condition = Specification.where(alwaysTrue())
				.and(StringUtils.isNoneBlank(firstName) ? StudentSpecification.equalFirstName(firstName) : null)
				.and(StringUtils.isNoneBlank(lastName) ? StudentSpecification.equalLastName(lastName) : null);
		
		return condition;
	}

}
