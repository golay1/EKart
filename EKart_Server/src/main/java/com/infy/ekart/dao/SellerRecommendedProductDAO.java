package com.infy.ekart.dao;

import java.util.List;

import com.infy.ekart.model.Product;

public interface SellerRecommendedProductDAO {

	public List<Product> getRecommendedProductList( String emailId);

	public int deleteRecommendedProduct(String emailId, int productId);
	
	public void addRecommendedProduct(String emailId, int productId) throws Exception;

}
