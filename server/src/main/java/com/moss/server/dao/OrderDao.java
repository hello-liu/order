package com.moss.server.dao;

import com.moss.server.model.Order;
import java.util.List;

public interface OrderDao {


	int add(Order order);
	int del(Integer id);
	int update(Order order);
	int updateFlag(Order order);
	List<Order> list(Order order);

}