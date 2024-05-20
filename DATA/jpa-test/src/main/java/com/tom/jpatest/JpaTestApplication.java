package com.tom.jpatest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;

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
	
	@Autowired
	private JdbcTemplate template;
	
	@Bean
	public CommandLineRunner commandLineRunner() {
		return runner -> {
//			findAll();
//			findById();
//			delete();
//			deleteById();
//			deleteAllById();
//			deleteSomeProduct();
//			deleteAllProduct();
//			deleteAllByIdInBatch();
//			deleteAllInBatch();
//			deleteSomeInBatch();
			
//			findByPageable();
//			finddeleteFlagProduct();
			
//			insertProduct();
//			insertProducts();
//			updateProduct();
//			updateProducts();
//			count();
			
			
			
			
//			test();
			// TODO
//			findAllwithDeleted();
			
			/** Native Query */
//			queryNative(); // TODO mapping 回物件
			
			
			/** JPQL */
			findByName();
			
			/** Native Query */
//			findPage();
//			findPage2();
			
			test();

		};
	}
	
	
	
	
	private void test() {
		Integer count = template.queryForObject(
                "SELECT COUNT(0) FROM PRODUCT", Integer.class);
		System.out.println("count: " + count);
	}




	private void findPage2() {
		PageRequest pageable = PageRequest.of(0, 200);
		List product = dao.findByPage(pageable);
		product.forEach(System.out::println);
	}




	private void findPage() {
		List products = dao.findByPage(0);
		products.forEach(System.out::println);
	}




	private void findByName() {
		List<Product> products = repository.findByName("柳橙汁sss");
		products.isEmpty();
		products.forEach(System.out::println);
	}
//	private void queryNative() {
//		List result = dao.executeQuery("SELECT * FROM PRODUCT");
//		result.forEach(System.out::println);
//	}


	private void deleteById() {
		repository.deleteById(8L);
	}

	private void deleteAllById() {
		List list = new ArrayList<Long>();
		list.add(2L); list.add(4L); list.add(5L); list.add(6L);
		repository.deleteAllById(list);
	}
	
	private void deleteAllByIdInBatch() {
		List list = new ArrayList<Long>();
		list.add(2L); list.add(4L); list.add(10L); list.add(11L);
		repository.deleteAllByIdInBatch(list);
	}
	
	private void deleteAllInBatch() {
		repository.deleteAllInBatch();
	}
	
	private void deleteSomeInBatch() {
		Optional<Product> o1 = repository.findById(108L);
		Optional<Product> o2 = repository.findById(109L);
		Optional<Product> o3 = repository.findById(4L);
		Optional<Product> o4 = repository.findById(5L);
		Product p1 = o1.get();
		Product p2 = o2.get();
		List<Product> products = new ArrayList<Product>();
		products.add(p1);products.add(p2);products.add(null);products.add(null);
		repository.deleteAllInBatch(products);
//		repository.deleteAll(products);
	}

	private void deleteList() {
		repository.deleteById(null);
		repository.deleteAllById(null);
		repository.deleteAllByIdInBatch(null);
		repository.deleteAllInBatch();
		repository.deleteAllInBatch(null);
		repository.deleteInBatch(null);
	}
	private void findAllwithDeleted() {
		List<Product> products = repository.findAllWithDeleted();
		System.out.println("");
		products.forEach(System.out::println);
	}
	private void count() {
		long count = repository.count();
		System.out.println("count: " + count);
	}
	private void updateProducts() {
		Optional<Product> o1 = repository.findById(2L);
		Optional<Product> o2 = repository.findById(3L);
		Optional<Product> o3 = repository.findById(4L);
		Optional<Product> o4 = repository.findById(5L);
		Product p1 = o1.get();
		p1.setName("蘋果ㄓ");
		p1.setModifier("Marry");
		Product p2 = o2.get();
		p2.setName("香蕉ㄓ");
		p2.setModifier("Marry");
		Product p3 = o3.get();
		p3.setName("吉子ㄓ");
		p3.setModifier("Marry");
		Product p4 = o4.get();
		p4.setName("葡萄ㄓ");
		p4.setModifier("Marry");
		
		List<Product> products = new ArrayList<Product>();
		products.add(p1);products.add(p2);products.add(p3);products.add(p4);
		repository.saveAll(products);
	}
	private void updateProduct() {
		Optional<Product> p = repository.findById(1L);
		Product product = p.get();
		product.setName("富士山蘋果");
		repository.save(product);
	}
	private void insertProducts() {
		List<Product> products = new ArrayList<Product>();
		Product p1 = new Product();
		p1.setName("多多綠茶");
		p1.setPrice(65);
		p1.setModifier("Tom");
		p1.setCreator("Tom");
		Product p2 = new Product();
		p2.setName("四季珍椰青");
		p2.setPrice(45);
		p2.setModifier("Tom");
		p2.setCreator("Tom");
		Product p3 = new Product();
		p3.setName("甘蔗春烏龍");
		p3.setPrice(65);
		p3.setModifier("Tom");
		p3.setCreator("Tom");
		products.add(p1); products.add(p2); products.add(p3);
		
		repository.saveAll(products);
		
	}
	private void insertProduct() {
		Product product = new Product();
		product.setName("珍珠奶茶");
		product.setPrice(65);
		product.setModifier("Tom");
		product.setCreator("Tom");
		Product save = repository.save(product);
	}

	private void deleteAllProduct() {
		repository.deleteAll();
	}

	private void deleteSomeProduct() {
		Optional<Product> o1 = repository.findById(2L);
		Optional<Product> o2 = repository.findById(3L);
		Optional<Product> o3 = repository.findById(4L);
		Optional<Product> o4 = repository.findById(5L);
		Product p1 = o1.get();
		p1.setName("蘋果ㄓ");
		p1.setModifier("Marry");
		Product p2 = o2.get();
		p2.setName("香蕉ㄓ");
		p2.setModifier("Marry");
		Product p3 = o3.get();
		p3.setName("吉子ㄓ");
		p3.setModifier("Marry");
		Product p4 = o4.get();
		p4.setName("葡萄ㄓ");
		p4.setModifier("Marry");
		List<Product> products = new ArrayList<Product>();
		products.add(p1);products.add(p2);products.add(p3);products.add(p4);
		repository.deleteAll(products);
	}

	private void finddeleteFlagProduct() {
		Optional<Product> p = repository.findById(1L);
		System.out.println("is present: " + p.isPresent());
	}




	// TODO
	private void findByPageable() {
		PageRequest pageable = PageRequest.of(0, 10);
		Page<Product> all = repository.findAll(pageable);
//		all.forEach(System.out::println);
		List<Product> content = all.getContent();
		content.forEach(System.out::println);
	}





	private void delete() {
		Optional<Product> data = repository.findById(101L);
		if (data.isPresent()) {
			repository.delete(data.get());
		}
	}

	private void findById() {
		Optional<Product> data = repository.findById(101L);
		System.out.println(data);
		
	}
	
	private void findAll() {
		List<Product> all = repository.findAll();
		all.forEach(System.out::println);
	}
}



























