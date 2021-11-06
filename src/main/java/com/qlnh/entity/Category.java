package com.qlnh.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity 
@Table(name = "Categories")
@Getter
@Setter
public class Category implements Serializable{
	@Id
	private Integer id;
	
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy = "category")  //neu ko fetch thì nó sẽ gen list product ra luôn
	private List<Product> products;
}
