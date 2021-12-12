package com.qlnh.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity @Table(name = "Products")
@Getter
@Setter
public class Product  implements Serializable{
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "categoryid")
	private Category category;
	
	private String name;
	
	private Double price;
	
	private Integer quantity;
	
	private String describe;
	
	private Long discount;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "createdate")
	private Date createDate = new Date();
	
	@Temporal(TemporalType.DATE)
	@Column(name = "isdelete")
	private Date isDelete = new Date();  //why init new date()
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<OrderDetail> orderDetails;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<Favorite> productid;
	
	private String photo;
	private String photo2;
	private String photo3;
	private String photo4;
	
}
