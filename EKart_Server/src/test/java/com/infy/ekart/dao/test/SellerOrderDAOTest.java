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

import com.infy.ekart.dao.SellerOrderDAO;
import com.infy.ekart.model.OrderStatus;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@Rollback(true)
public class SellerOrderDAOTest {
	
	@Autowired
	private SellerOrderDAO sellerOrderDAO;
	
	@Test

	public void testModifyOrderStatus(){
		sellerOrderDAO.modifyOrderStatus(90000, OrderStatus.CANCELLED);
	}
	
	@Test
	public void testGetOrdersForProducts(){
		List<Integer> productId=new ArrayList<Integer>();
		productId.add(1044);
		productId.add(1052);
		Assert.assertNotNull(sellerOrderDAO.getOrdersForProducts(productId));
	}
}


