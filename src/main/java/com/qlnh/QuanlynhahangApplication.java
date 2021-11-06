package com.qlnh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.qlnh.dao.OrderDAO;

@SpringBootApplication
public class QuanlynhahangApplication {

	@Autowired
	OrderDAO orderDao;
	public static void main(String[] args) {
		SpringApplication.run(QuanlynhahangApplication.class, args);
		//System.out.print("list account: "+orderDao.);
	}

}
