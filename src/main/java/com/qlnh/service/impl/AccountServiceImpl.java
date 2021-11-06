package com.qlnh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qlnh.dao.AccountDAO;
import com.qlnh.entity.Account;
import com.qlnh.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountDAO adao;

	@Override
	public Account findById(String username) {
		return adao.findById(username).get();
	}
	
	public List<Account> findAll(){
		return adao.findAll();
	}
	
	public List<Account> getAdministrators(){
		return adao.getAdministrators();
	}
}
