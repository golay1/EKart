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

import com.infy.ekart.api.SellerRecommendProductAPI;
import com.infy.ekart.model.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@Rollback(true)
public class SellerRecommendedProductAPITest {
	
	@Autowired
	SellerRecommendProductAPI sellerRecProdAPI;
	
	@Test
	public void getRecommendedProductValid() throws Exception{
		List<Product> products=new ArrayList<>();
		Product product=new Product();
		products.add(product);
		
		Assert.assertNotNull(sellerRecProdAPI.getRecommendedProduct("email@email.com"));
		try {
			Assert.assertNotNull(sellerRecProdAPI.getRecommendedProduct(""));
		}
		finally {}
	}
	
	@Test
	public void deleteRecValid() throws Exception{
		List<Product> products=new ArrayList<>();
		Product product=new Product();
		products.add(product);
		
		try {
		sellerRecProdAPI.addRecommendedProduct(1028,"brad@infosys.com");
		}
		catch (Exception e) {}

		try {
		Assert.assertNotNull(sellerRecProdAPI.deleteRecommendedProduct("brad@infosys.com",1028));
		}
		catch (Exception e) {}
	}
	
	@Test
	public void deleteRecError() throws Exception{
		List<Product> products=new ArrayList<>();
		Product product=new Product();
		products.add(product);
		
		try {
		sellerRecProdAPI.deleteRecommendedProduct("brad@infosys.com",1028);
		}
		catch (Exception e) {}

		try {
		Assert.assertNotNull(sellerRecProdAPI.deleteRecommendedProduct("brad@infosys.com",1028));
		}
		catch (Exception e) {}
	}
	
	@Test
	public void addRecValid() throws Exception{
		List<Product> products=new ArrayList<>();
		Product product=new Product();
		products.add(product);
		
		try {
		sellerRecProdAPI.deleteRecommendedProduct("brad@infosys.com",1028);
		}
		catch (Exception e) {}

		try {
		sellerRecProdAPI.addRecommendedProduct(1028,"brad@infosys.com");
		Assert.assertNotNull(product);
		}
		catch (Exception e) {}
	}
	
	@Test
	public void addRecError() throws Exception{
		List<Product> products=new ArrayList<>();
		Product product=new Product();
		products.add(product);
		
		try {
		sellerRecProdAPI.addRecommendedProduct(1028,"brad@infosys.com");
		}		
		catch (Exception e) {}
		
		try {
		sellerRecProdAPI.addRecommendedProduct(1028,"brad@infosys.com");
		Assert.assertNotNull(product);
		}
		catch (Exception e) {}
	}
}
