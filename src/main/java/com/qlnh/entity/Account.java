package com.qlnh.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
}
