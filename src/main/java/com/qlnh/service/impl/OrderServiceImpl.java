package com.qlnh.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qlnh.dao.OrderDAO;
import com.qlnh.dao.OrderDetailDAO;
import com.qlnh.entity.Order;
import com.qlnh.entity.OrderDetail;
import com.qlnh.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderDAO dao;
	@Autowired
	OrderDetailDAO ddao;

	@Override
	public Order create(JsonNode orderData) {
		ObjectMapper mapper = new ObjectMapper();

		Order order = mapper.convertValue(orderData, Order.class);
		dao.save(order);

		TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {
		};
		List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type).stream()
				.peek(d -> d.setOrder(order)).collect(Collectors.toList());
		ddao.saveAll(details);
		return order;
	}

	@Override
	public Order findById(Long id) {
		return dao.findById(id).get();
	}

	@Override
	public List<Order> findByUsername(String username) {
		return dao.findByUsername(username);
	}

	@Override
	public List<Order> findAll() {
		return dao.findAll();
	}

}
