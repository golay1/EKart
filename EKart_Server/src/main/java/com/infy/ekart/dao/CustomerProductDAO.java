package com.infy.ekart.dao;

import java.util.List;

import com.infy.ekart.model.Product;

public interface CustomerProductDAO {
	public List<Product> getAllProducts();
	public List<Product> getRecProducts();
}
