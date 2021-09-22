package com.infy.ekart.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.infy.ekart.entity.OrderEntity;
import com.infy.ekart.entity.ProductEntity;
import com.infy.ekart.model.Order;
import com.infy.ekart.model.OrderStatus;
import com.infy.ekart.model.Product;

@Repository(value = "sellerOrderDAO")
public class SellerOrderDAOImpl implements SellerOrderDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public void modifyOrderStatus(Integer orderId, OrderStatus orderStatus) {

		OrderEntity orderEntity = entityManager.find(OrderEntity.class, orderId);
		
		orderEntity.setOrderStatus(orderStatus);
		
	}

	@Override
	public List<Order> getOrdersForProducts(List<Integer> productIds) {
		
		List<OrderEntity> allOrderEntities = new ArrayList<>();
		
		for (Integer productId : productIds) {
			Query query = entityManager.createQuery("select o from OrderEntity o where o.productEntity.productId = :productId");
			query.setParameter("productId", productId);
			List<OrderEntity> orderEntities = query.getResultList();
			
			allOrderEntities.addAll(orderEntities);
		}
		
		List<Order> orders = new ArrayList<>();
		for (OrderEntity orderEntity : allOrderEntities) {
			Order order = new Order();
			order.setAddressId(orderEntity.getAddressId());
			order.setDateOfOrder(orderEntity.getDateOfOrder());
			order.setOrderId(orderEntity.getOrderId());
			order.setOrderNumber(orderEntity.getOrderNumber());
			order.setOrderStatus(orderEntity.getOrderStatus().toString());
				ProductEntity productEntity = orderEntity.getProductEntity();
				Product product = new Product();
				product.setBrand(productEntity.getBrand());
				product.setCategory(productEntity.getCategory());
				product.setDescription(productEntity.getDescription());
				product.setDiscount(productEntity.getDiscount());
				product.setName(productEntity.getName());
				product.setPrice(productEntity.getPrice());
				product.setProductId(productEntity.getProductId());
				product.setQuantity(productEntity.getQuantity());
			order.setProduct(product);
			order.setQuantity(orderEntity.getQuantity());
			order.setTotalPrice(orderEntity.getTotalPrice());
			order.setPaymentThrough(orderEntity.getPaymentThrough());
			order.setDataOfDelivery(orderEntity.getDataOfDelivery());
			orders.add(order);
		}
		
		return orders;
	}

}
