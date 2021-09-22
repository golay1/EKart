package com.infy.ekart.dao;

import java.util.List;

import com.infy.ekart.model.Product;

public interface SellerProductDAO {

	public Integer addNewProduct(Product product);
	
	public Product modifyProductDetails(Product product);
	
	public Integer removeProduct(String sellerEmailId, Integer productId);
	
	public List<String> getProductCategoryList();
}
