package com.tom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tom.entity.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {

}
