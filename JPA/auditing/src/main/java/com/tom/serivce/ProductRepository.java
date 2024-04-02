package com.tom.serivce;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tom.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
