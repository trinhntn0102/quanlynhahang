package com.qlnh.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qlnh.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity 
@Table(name = "Accounts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Account implements Serializable{
	@Id
	private String username;
	
	private String password;
	
	private String fullname;
	
	private String email;
	
	private String photo;
	
	private Boolean gender;
	
	private String address;
	
	private Boolean activated;
	
	@Column(name = "phonenumber")
	private String phoneNumber;
	
	@Column(name = "createdate")
	private Date createDate;
	
//	@Column(name = "deletedate")
//	private Date deleteDate;
//	
//	@Column(name = "updatedate")
//	private Date UpdateDate;
	
	@Column(name = "isdelete")
	private Boolean isDelete;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
	List<Authority> authorities;
	
	@JsonIgnore
	@OneToMany(mappedBy = "username")
	List<Favorite> fav;
	
}
