package com.infy.ekart.service;

import java.util.List;

import com.infy.ekart.model.Product;

public interface SellerProductService {
	
	public Integer addNewProduct(Product product) throws Exception;
	
	public Product modifyProductDetails(Product product) throws Exception;
	
	public Integer removeProduct(Product product) throws Exception;
	
	public List<String> getProductCategoryList() throws Exception;
}
