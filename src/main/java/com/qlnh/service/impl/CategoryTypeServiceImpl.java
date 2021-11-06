package com.qlnh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qlnh.dao.CategoryTypeDAO;
import com.qlnh.entity.Category;
import com.qlnh.entity.CategoryType;
import com.qlnh.service.CategoryTypeService;

@Service
public class CategoryTypeServiceImpl implements CategoryTypeService{
	@Autowired
	CategoryTypeDAO cTypeDao;

	@Override
	public List<CategoryType> findAll() {
		return cTypeDao.findAll();
	}

	@Override
	public CategoryType findById(Integer id) {
		return cTypeDao.findById(id).get();
	}

	@Override
	public CategoryType create(Category category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryType update(Category category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CategoryType> findByCategoryId(Integer id) {
		List<CategoryType> ctypeList = cTypeDao.findByCategoryId(id);
		return ctypeList;
	}
	
}
