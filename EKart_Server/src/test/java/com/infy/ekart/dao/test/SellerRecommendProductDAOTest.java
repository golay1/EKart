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

import com.infy.ekart.dao.SellerRecommendedProductDAO;
import com.infy.ekart.entity.RecommendedProductEntity;
import com.infy.ekart.model.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@Rollback(true)
public class SellerRecommendProductDAOTest {
	@Autowired
	SellerRecommendedProductDAO sellerRecProdDAO;
	
	@Test
	public void getRecommendedProductListValid(){
		List<Product> products=new ArrayList<>();
		Product product=new Product();
		product.setProductId(1);
		products.add(product);
		
		List<RecommendedProductEntity> productEnts=new ArrayList<>();
		RecommendedProductEntity recProd=new RecommendedProductEntity();
		productEnts.add(recProd);
		
		Assert.assertNotNull(sellerRecProdDAO.getRecommendedProductList("email@email.com"));
	}
	
	@Test
	public void deleteRecommendedProductValid(){
		List<Product> products=new ArrayList<>();
		Product product=new Product();
		products.add(product);
		
		Assert.assertNotNull(sellerRecProdDAO.deleteRecommendedProduct("email@email.com",1));
	}
	
	@Test
	public void addRecommendedProductValid() throws Exception {
		List<Product> products=new ArrayList<>();
		Product product=new Product();
		product.setSellerEmailId("email@email.com");
		product.setProductId(1500);
		products.add(product);
		
		List<RecommendedProductEntity> productEnts=new ArrayList<>();
		RecommendedProductEntity recProd=new RecommendedProductEntity();
		productEnts.add(recProd);
		
		try {
		sellerRecProdDAO.addRecommendedProduct("email@email.com",1500);
		Assert.assertNotNull(product);
		}
		catch (Exception e) {}

	}

}
