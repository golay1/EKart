package com.infy.ekart.api.test;

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

import com.infy.ekart.api.CustomerProductAPI;
import com.infy.ekart.model.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@Rollback(true)
public class CustomerProductAPITest {
	
	@Autowired
	CustomerProductAPI customerProductAPI;
	
	@Test
	public void getAllProductsValid() throws Exception{
		List<Product> products=new ArrayList<>();
		Product product=new Product();
		products.add(product);
		
		Assert.assertNotNull(customerProductAPI.getAllProducts());
	}
	
	@Test
	public void getRecProductsValid() throws Exception{
		List<Product> products=new ArrayList<>();
		Product product=new Product();
		products.add(product);
		
		Assert.assertNotNull(customerProductAPI.getRecProducts());
	}

}
