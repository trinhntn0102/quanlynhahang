package com.qlnh.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qlnh.dto.Report;
import com.qlnh.entity.OrderDetail;
import org.springframework.data.domain.Pageable;
public interface OrderDetailDAO extends JpaRepository<OrderDetail, Integer> {
	@Query("SELECT sum(o.price * o.quantity) FROM OrderDetail o")
	double priceTotal(); 
	
	@Query("SELECT sum(o.quantity) FROM OrderDetail o")  //thuộc tính phải viết thường
	int qtyTotal(); 
	 
	@Query("SELECT  new Report(o.product, sum(o.quantity))" 
			+ " FROM OrderDetail o"
			+ " GROUP BY o.product.id")
	List<Report> findAllByASC(Pageable pageable);
	
	@Query("SELECT  new Report(o.product, sum(o.quantity))" 
			+ " FROM OrderDetail o"
			+ " GROUP BY o.product.id")
	List<Report> findAllByASC();

	@Query("SELECT o FROM OrderDetail o WHERE o.order.id=?1")
	List<OrderDetail> findById(Long odid);
	
	@Query("SELECT o FROM OrderDetail o WHERE o.order.account.username=?1")
	List<OrderDetail> findByUsername(String username);
	
	@Query("SELECT sum(o.price * o.quantity) FROM OrderDetail o WHERE o.order.id=?1")
	double priceTotalOrder(Long odid);
	 
	
	
}
