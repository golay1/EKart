package com.infy.ekart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.ekart.dao.CustomerProductDAO;
import com.infy.ekart.model.Product;

@Service(value = "customerProductService")
@Transactional
public class CustomerProductServiceImpl implements CustomerProductService {

	@Autowired
	private CustomerProductDAO customerProductDAO;
	
	@Override
	public List<Product> getAllProducts() throws Exception {

		List<Product> products =null;
		products = customerProductDAO.getAllProducts();
		return products;	
		
	}
	
	@Override
	public List<Product> getRecProducts() throws Exception {

		List<Product> products =null;
		products = customerProductDAO.getRecProducts();
		return products;	
		
	}
}
