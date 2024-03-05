package com.tom.jpatest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.tom.jpatest.entity.Product;
import com.tom.jpatest.repository.ProductRepository;
import com.tom.jpatest.service.AppDAO;

@SpringBootApplication
public class JpaTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaTestApplication.class, args);
	}

	@Autowired
	private AppDAO dao;
	
	@Autowired
	private ProductRepository repository;
	
	@Bean
	public CommandLineRunner commandLineRunner() {
		return runner -> {
			
			
//			findAll();
//			findById();
			deleteById();
			
		};
	}





	private void deleteById() {
		Optional<Product> data = repository.findById(101L);
		if (data.isPresent()) {
			repository.softDelete(data.get());
		}
	}

	private void findById() {
		Optional<Product> data = repository.findById(101L);
		System.out.println(data);
		
	}
	
	private void findAll() {
		Iterable<Product> all = repository.findAll();
		all.forEach(System.out::println);
	}
}



























