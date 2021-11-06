package com.qlnh.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qlnh.dao.AccountDAO;
import com.qlnh.dao.AuthorityDAO;
import com.qlnh.dao.RoleDAO;
import com.qlnh.entity.Account;
import com.qlnh.entity.Authority;
import com.qlnh.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService {
	@Autowired
	AuthorityDAO dao;
	@Autowired
	AccountDAO acdao;
	@Autowired
	RoleDAO roledao;


	public List<Authority> findAll() {
		return dao.findAll();
	}

	public Authority create(Authority auth) {
		return dao.save(auth);
	}

	public void delete(Integer id) {
		dao.deleteById(id);
	}

	public List<Authority> findAuthoritiesOfAdministrators() {
		List<Account> accounts = acdao.getAdministrators();
		return dao.authoritiesOf(accounts);
	}

	public Map<String, Object> getAuthorities(){
		Map<String, Object> data= new HashMap<>();
		data.put("authorities", dao.findAll());
		data.put("roles", roledao.findAll());
		data.put("accounts", acdao.findAll());
		System.out.println("data: "+acdao.findAll());
		return data;
	}
	
	

}
