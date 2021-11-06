package com.qlnh.service;

import java.util.List;

import com.qlnh.entity.Account;

public interface AccountService {

	public Account findById(String username);
	public List<Account> findAll();
	public List<Account> getAdministrators();
}
