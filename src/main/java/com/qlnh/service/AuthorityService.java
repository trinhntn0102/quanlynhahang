package com.qlnh.service;

import java.util.List;
import java.util.Map;
import com.qlnh.entity.Authority;

public interface AuthorityService {
	public List<Authority> findAll();

	public Authority create(Authority auth);

	public void delete(Integer id);

	public List<Authority> findAuthoritiesOfAdministrators();

	public Map<String, Object> getAuthorities();
}
