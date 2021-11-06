package com.qlnh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qlnh.dao.RoleDAO;
import com.qlnh.entity.Role;
import com.qlnh.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleDAO dao;

	public List<Role> findAll() {
		return dao.findAll();
	}
}
