package com.tom.serivce;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tom.entity.Product;
import com.tom.entity.ProductDTO;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository repository;

	@Override
	public List<Product> findAll() {
		List<Product> all = repository.findAll();
		return all;
	}

	@Override
	public void insertProduct(ProductDTO product) {
		Product p = new Product();
		BeanUtils.copyProperties(product, p);
		repository.save(p);
	}

	@Override
	public void updateProduct(ProductDTO product) {
		Long pid = product.getPid();
		Optional<Product> opt = repository.findById(pid);
		
		if (opt.isPresent()) {
			Product p = opt.get();
			BeanUtils.copyProperties(product, p);
			repository.save(p);
		}
	}
	
	
	
	
	
	
}
