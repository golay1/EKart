package com.infy.ekart.dao.test;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.infy.ekart.dao.CustomerCartDAO;
import com.infy.ekart.model.CustomerCart;
import com.infy.ekart.model.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@Rollback(true)
public class CustomerCartDAOTest {
	
	@Autowired
	private CustomerCartDAO customerCartDAO;
	
	@Test
	public void getCustomerCartsValid(){
		
		Assert.assertNotNull(customerCartDAO.getCustomerCarts("tom@infosys.com"));
	}
	
	@Test
	public void getProductById(){
		
		Assert.assertNotNull(customerCartDAO.getProductById(1000));
	}
	
	@Test
	public void getProductByIdInvalid(){
		
		Assert.assertNull(customerCartDAO.getProductById(100000));
	}
	
	@Test
	public void deleteProductFromCart(){
		
		customerCartDAO.deleteProductFromCart("tom@infosys.com", 3000);
		
	}
	
	@Test
	public void modifyQuantityOfProductInCart(){
		
		customerCartDAO.modifyQuantityOfProductInCart(3000, 2);
	}
	
	@Test
	public void addProductToCart(){
		CustomerCart customerCart=new CustomerCart();
		customerCart.setCartId(3000);
		Product p =new Product();
		p.setProductId(1001);
		customerCart.setProduct(p);
		customerCart.setQuantity(1);
		customerCartDAO.addProductToCart("tom@infosys.com", customerCart);
	}

}
