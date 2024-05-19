package com.tom.condition;

import java.io.Serializable;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public interface SearchCondition<T> extends Serializable {

    Specification<T> getSpecification();
    
    default Specification<T> alwaysTrue() {
        return new Specification<T>() {
            private static final long serialVersionUID = 1L;

			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.conjunction();
            }
        };
    }
    
    default Specification<T> alwaysFalse() {
        return new Specification<T>() {
            private static final long serialVersionUID = 1L;

			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.disjunction();
            }
        };
    }

}
