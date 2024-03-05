package com.tom.jpatest.repository;

import org.springframework.stereotype.Repository;

import com.tom.jpatest.entity.Product;

@Repository
public interface ProductRepository extends SoftDeleteRepository<Product, Long> {

}
