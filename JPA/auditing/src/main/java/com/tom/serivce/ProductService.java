package com.tom.serivce;

import java.util.List;

import com.tom.entity.Product;
import com.tom.entity.ProductDTO;

public interface ProductService {

	List<Product> findAll();
	
	void insertProduct(ProductDTO product);
	
	void updateProduct(ProductDTO product);
}
