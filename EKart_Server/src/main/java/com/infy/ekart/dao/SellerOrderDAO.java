package com.infy.ekart.dao;

import java.util.List;

import com.infy.ekart.model.Order;
import com.infy.ekart.model.OrderStatus;


public interface SellerOrderDAO {

	public void modifyOrderStatus(Integer orderId, OrderStatus orderStatus);
	
	public List<Order> getOrdersForProducts(List<Integer> productIds);
}
