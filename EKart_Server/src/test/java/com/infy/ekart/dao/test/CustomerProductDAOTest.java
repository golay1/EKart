package com.infy.ekart.dao.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.infy.ekart.dao.CustomerProductDAO;
import com.infy.ekart.model.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@Rollback(true)
public class CustomerProductDAOTest {
	@Autowired
	CustomerProductDAO customerProductDAO;
	@Test
	public void getAllProductsValid(){
		List<Product> products=new ArrayList<>();
		Product product=new Product();
		products.add(product);
		
		Assert.assertNotNull(customerProductDAO.getAllProducts());
	}
	
	@Test
	public void getRecProductsValid(){
		List<Product> products=new ArrayList<>();
		Product product=new Product();
		products.add(product);
		
		Assert.assertNotNull(customerProductDAO.getRecProducts());
	}

}
