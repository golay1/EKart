package com.infy.ekart.service;

import java.util.List;

import com.infy.ekart.model.Product;

public interface SellerRecommendProductService {
	
	public List<Product> getRecommendedProductList(String emailId) throws Exception;
	
	public int deleteRecommendedProduct(String emailId, int productId) throws Exception;
	
	public void addRecommendedProduct(int productId, String emailId) throws Exception ;
}
