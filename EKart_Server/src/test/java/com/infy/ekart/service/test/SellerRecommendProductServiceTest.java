package com.infy.ekart.service.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.infy.ekart.dao.SellerRecommendedProductDAO;
import com.infy.ekart.model.Product;
import com.infy.ekart.service.SellerRecommendProductService;
import com.infy.ekart.service.SellerRecommendProductServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerRecommendProductServiceTest {
	@Mock
	private SellerRecommendedProductDAO sellerRecProdDAO;
	

	@InjectMocks
	private SellerRecommendProductService sellerRecProdService=new SellerRecommendProductServiceImpl();
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	
	@Test
	public void getRecommendedProductListValid() throws Exception {
		
		List<Product> products=new ArrayList<Product>();
		Product product=new Product();
		product.setProductId(1);
		products.add(product);
		Mockito.when(sellerRecProdDAO.getRecommendedProductList("email@email.com")).thenReturn(products);
		Assert.assertNotNull(sellerRecProdService.getRecommendedProductList("email@email.com"));
	}
		
	@Test
	public void deleteRecommendedProductValid() throws Exception {
		
		List<Product> products=new ArrayList<Product>();
		Product product=new Product();
		product.setProductId(1);
		products.add(product);
		try {
		Mockito.when(sellerRecProdDAO.deleteRecommendedProduct("email@email.com",1)).thenReturn(0);
		Assert.assertNotNull(sellerRecProdService.deleteRecommendedProduct("email@email.com",1));
		}
		catch (Exception e)
		{}
	}
	
	@Test
	public void addRecommendedProductValid() throws Exception {
		
		List<Product> products=new ArrayList<Product>();
		Product product=new Product();
		product.setProductId(1);
		products.add(product);
		sellerRecProdService.addRecommendedProduct(1,"email@email.com");
		Assert.assertNotNull(product);
	}
	
}
