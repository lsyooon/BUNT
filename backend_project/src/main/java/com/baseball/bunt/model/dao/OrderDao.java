package com.baseball.bunt.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baseball.bunt.model.dto.shop.Order;

@Repository
public interface OrderDao {

	int insertOrder(Order order);

	int updateStatus(Order order);

	Order selectOrderByOrderId(int orderId);

	List<Order> selectOrderByOrderDate(String orderDate);

	int deleteOrder(int orderId);

	int getTotalPrice();
}
