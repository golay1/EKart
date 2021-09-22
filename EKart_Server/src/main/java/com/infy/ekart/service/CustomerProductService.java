package com.infy.ekart.service;

import java.util.List;

import com.infy.ekart.model.Product;

public interface CustomerProductService {
	public List<Product> getAllProducts() throws Exception;
	public List<Product> getRecProducts() throws Exception;
}
