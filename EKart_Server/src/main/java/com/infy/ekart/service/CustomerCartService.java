package com.infy.ekart.service;

import java.util.List;

import com.infy.ekart.model.CustomerCart;

public interface CustomerCartService {

	public void addProductToCart(String customerEmailId, CustomerCart customerCart) throws Exception;
	public List<CustomerCart> getCustomerCarts(String customerEmailId) throws Exception;
	public void modifyQuantityOfProductInCart(Integer cartId, Integer quantity, Integer productId) throws Exception;
	public void deleteProductFromCart(String customerEmailId, Integer cartId);
	
}
