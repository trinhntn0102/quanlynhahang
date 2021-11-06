package com.qlnh.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qlnh.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Integer> {
	@Query("SELECT p FROM Product p WHERE p.category.id=?1")
	List<Product> findByCategory(String cid);
	
	
	@Query("SELECT p FROM Product p WHERE p.name like ?1")
	List<Product> findByName(String name);


	//List<Product> findByName(String name);
	
	//top sản phẩm bán ế
//	@Query("SELECT p FROM Product p desc")
//	List<Product> findAllByDesc();
	
}
