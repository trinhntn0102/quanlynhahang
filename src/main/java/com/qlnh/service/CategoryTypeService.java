package com.qlnh.service;

import java.util.List;

import com.qlnh.entity.Category;
import com.qlnh.entity.CategoryType;

public interface CategoryTypeService {

	List<CategoryType> findAll(); 

	CategoryType findById(Integer id);

	CategoryType create(Category category);

	CategoryType update(Category category);

	void delete(String id);
	
	List<CategoryType> findByCategoryId(Integer id);
}
