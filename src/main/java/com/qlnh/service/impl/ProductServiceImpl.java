package com.qlnh.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.qlnh.dao.ProductDAO;
import com.qlnh.entity.Product;
import com.qlnh.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDAO pdao;

	@Override
	public List<Product> findAll() {
		return pdao.findAll();
	}

	@Override
	public Product findById(Integer id) {
		return pdao.findById(id).get();
	}

	@Override
	public List<Product> findByCategory(Integer cid) {
		return pdao.findByCategory(cid);
	}

	@Override
	public Product create(Product product) {
		return pdao.save(product);
	}

	@Override
	public Product update(Product product) {
		return pdao.save(product);
	}

	public void delete(Integer id) {
		 pdao.deleteById(id);
	}

	@Override
	public List<Product> findByName(String name) {
		return pdao.findByName(name);
	}

	@Override
	public Page<Product> pageAll(Pageable page) {
		return pdao.findAll(page);
	}

	
	public Page<Product> pageCateAll(Pageable pagecate, Integer id) {
		return pdao.findByCategory(pagecate, id);
	}

	public Page<Product> findByName(Pageable pageable, String name) {
		return pdao.findByName(pageable, name);
	}

	public Product finById(Integer id) {
		return pdao.findById(id).get();
	}
	

}
