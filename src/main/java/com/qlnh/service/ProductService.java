package com.qlnh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.qlnh.entity.Product;

public interface ProductService {

	List<Product> findAll();

	List<Product> findByCategory(Integer cid);

	Product create(Product product);

	Product update(Product product);

	void delete(Integer id);
	
	List<Product> findByName(String name);

	Page<Product> pageAll(Pageable page);
	
	Page<Product> pageCateAll(Pageable pagecate, Integer id);

	Page<Product> findByName(Pageable pageable, String string);
	
	Product findById(Integer id);
}
