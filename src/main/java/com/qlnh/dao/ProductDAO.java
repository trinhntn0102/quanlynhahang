package com.qlnh.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qlnh.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Integer> {
	@Query("SELECT p FROM Product p WHERE p.category.id=?1")
	List<Product> findByCategory(Integer cid);

	@Query("SELECT p FROM Product p WHERE p.name like ?1")
	List<Product> findByName(String name);

	@Query("SELECT p FROM Product p WHERE p.category.id=2")
	List<Product> findByCategory2(Pageable pageable);

	@Query("SELECT p FROM Product p WHERE p.category.id=1")
	List<Product> findByCategory1(Pageable pageable);

	@Query("SELECT p FROM Product p WHERE p.category.id=3")
	List<Product> findByCategory3(Pageable pageable);

	@Query("SELECT p FROM Product p WHERE p.category.id=4")
	List<Product> findByCategory4(Pageable pageable);

	@Query("SELECT p FROM Product p WHERE p.category.id=5")
	List<Product> findByCategory5(Pageable pageable);

	@Query("SELECT p FROM Product p WHERE p.category.id=?1")
	Page<Product> findByCategory(Pageable pagecate, Integer id);

	@Query("SELECT p FROM Product p WHERE p.name like ?1")
	Page<Product> findByName(Pageable pageable, String name);


}
