package com.tom.jpatest.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tom.jpatest.entity.Product;

@Repository
public interface ProductRepository extends SoftDeleteRepository<Product, Long> {

	
	List<Product> findByName(String name);
}
