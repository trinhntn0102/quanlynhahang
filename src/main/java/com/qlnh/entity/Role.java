package com.qlnh.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.qlnh.entity.Authority;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role  implements Serializable{
	@Id
	private String id;
	private String name;
	@JsonIgnore
	@OneToMany(mappedBy = "role")
	List<Authority> authorities;
}