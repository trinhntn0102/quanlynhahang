package com.qlnh.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qlnh.entity.Role;
public interface RoleDAO extends JpaRepository<Role, Integer> {

}
