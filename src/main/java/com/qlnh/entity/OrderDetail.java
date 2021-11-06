package com.qlnh.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@SuppressWarnings("serial")
@Entity 
@Table(name = "Orderdetails")
@Getter
@Setter
public class OrderDetail  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double price;
	
	private Integer quantity;
	
	@ManyToOne
	@JoinColumn(name = "productid")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "orderid")
	private Order order;
}
