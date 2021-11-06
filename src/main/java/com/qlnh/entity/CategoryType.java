package com.qlnh.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity 
@Table(name = "categorytype") //ten table phai giong trong database
@Getter
@Setter
public class CategoryType {
	@Id
	private Integer id;
	
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "categoryid")
	private Category category;
}
