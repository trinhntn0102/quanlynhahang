package com.qlnh.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qlnh.entity.CategoryType;

public interface CategoryTypeDAO extends JpaRepository<CategoryType, Integer>{
	@Query("SELECT ctype FROM CategoryType ctype WHERE ctype.category.id=?1")
	List<CategoryType> findByCategoryId(Integer categoryId);

}
