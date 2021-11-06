package com.qlnh.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.qlnh.entity.Product;

public interface ProductService {

	List<Product> findAll();

	Product findById(Integer id);

	List<Product> findByCategory(String cid);

	Product create(Product product);

	Product update(Product product);

	void delete(Integer id);
	
	List<Product> findByName(String name);


}
