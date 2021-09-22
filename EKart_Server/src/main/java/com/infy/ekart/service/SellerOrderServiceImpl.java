package com.infy.ekart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.ekart.dao.SellerDAO;
import com.infy.ekart.dao.SellerOrderDAO;
import com.infy.ekart.model.Order;
import com.infy.ekart.model.OrderStatus;
import com.infy.ekart.model.Product;
import com.infy.ekart.model.Seller;

@Service(value = "sellerOrderService")
@Transactional
public class SellerOrderServiceImpl implements SellerOrderService {

	@Autowired
	private SellerOrderDAO sellerOrderDAO;
	
	@Autowired
	private SellerDAO sellerDAO;
	
	@Override
	public void modifyOrderStatus(Integer orderId, String orderStatus) throws Exception{
		
		sellerOrderDAO.modifyOrderStatus(orderId, OrderStatus.valueOf(orderStatus));
	}

	@Override
	public List<Order> viewOrders(String sellerEmailId) throws Exception {

		Seller seller = sellerDAO.getSellerByEmailId(sellerEmailId);
		
		List<Product> products = seller.getProducts();
		List<Integer> productIds = new ArrayList<>();
		for (Product product : products) {
			productIds.add(product.getProductId());
		}
		List<Order> ordersFromDB = sellerOrderDAO.getOrdersForProducts(productIds);
		if(ordersFromDB.isEmpty()){
			throw new Exception("SellerOrderService.NO_ORDERS_FOR_YOUR_PRODUCTS");
		}
		
		return ordersFromDB;
	}

}
