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

import com.infy.ekart.dao.SellerProductDAO;
import com.infy.ekart.entity.ProductCategoryEntity;
import com.infy.ekart.model.Product;
import com.infy.ekart.model.Seller;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@Rollback(true)
public class SellerProductDAOTest {
	
	@Autowired
	private SellerProductDAO sellerProductDAO;

	@Test
	public void modifyProductDetails(){
		Seller seller=new Seller();
		List<Product> products=new ArrayList<>();
		Product product=new Product();
		product.setBrand("Samsung");
		product.setCategory("Electronics - Mobile");
		product.setDescription("12MP camera");
		product.setDiscount(10.0);
		product.setName("Galaxy");
		product.setPrice(18500.0);
		product.setProductId(1005);
		product.setQuantity(10);
		
		products.add(product);
		seller.setProducts(products);
		sellerProductDAO.modifyProductDetails(product);
		Assert.assertTrue(true);
	}
	
	@Test
	public void addNewProduct(){
		Seller seller=new Seller();
		List<Product> products=new ArrayList<>();
		Product product=new Product();
		product.setBrand("Samsung");
		product.setCategory("Electronics - Mobile");
		product.setDescription("12MP camera");
		product.setDiscount(10.0);
		product.setName("Galaxy");
		product.setPrice(18500.0);
		product.setQuantity(10);
		product.setSellerEmailId("ken@infosys.com");
		products.add(product);
		seller.setProducts(products);
		sellerProductDAO.addNewProduct(product);
		Assert.assertTrue(true);
	}
	
	@Test
	public void removeProduct(){
		Seller seller=new Seller();
		List<Product> products=new ArrayList<>();
		Product product=new Product();
		product.setBrand("Samsung");
		product.setCategory("Electronics - Mobile");
		product.setDescription("12MP camera");
		product.setDiscount(10.0);
		product.setName("Galaxy");
		product.setPrice(18500.0);
		product.setProductId(1005);
		product.setQuantity(10);
		
		products.add(product);
		seller.setProducts(products);
		sellerProductDAO.removeProduct("jack@infosys.com",product.getProductId());
		Assert.assertTrue(true);
	}
	
	@Test
	public void getProductCategoryList(){
		List<ProductCategoryEntity> productCategoryEntityList=new ArrayList<>();
		List<String> productCategories = new ArrayList<>();
		
		for (ProductCategoryEntity productCategoryEntity : productCategoryEntityList) {
			productCategories.add(productCategoryEntity.getCategory());
		}
	
		sellerProductDAO.getProductCategoryList();
		Assert.assertTrue(true);
	}
	
	
	
}
