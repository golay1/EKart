package com.infy.ekart.service.test;

import static org.mockito.Mockito.when;

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

import com.infy.ekart.dao.SellerDAO;
import com.infy.ekart.dao.SellerOrderDAO;
import com.infy.ekart.model.Order;
import com.infy.ekart.model.OrderStatus;
import com.infy.ekart.model.Product;
import com.infy.ekart.model.Seller;
import com.infy.ekart.service.SellerOrderService;
import com.infy.ekart.service.SellerOrderServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerOrderServiceTest {
	@Mock
	private SellerOrderDAO sellerOrderDAO;
	
	@Mock
	private SellerDAO sellerDAO;
	
	@InjectMocks
	private SellerOrderService sellerOrderService=new SellerOrderServiceImpl();
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void testModifyOrderStatus() throws Exception{
		String status="DISPATCHED";
		Integer id=10;
		Mockito.doNothing().when(sellerOrderDAO).modifyOrderStatus(id, OrderStatus.valueOf(status));
		sellerOrderService.modifyOrderStatus(id, status);
		
	}
	@Test
	public void testModifyOrderStatusValid() throws Exception{
		String status="CANCELLED";
		Integer id=10;
		Mockito.doNothing().when(sellerOrderDAO).modifyOrderStatus(id, OrderStatus.valueOf(status));
		sellerOrderService.modifyOrderStatus(id, status);
		
	}
	@Test
	public void testViewOrdersValid() throws Exception{
		Product p=new Product();
		p.setProductId(2334);
		List<Product> products=new ArrayList<Product>();
		products.add(p);
		Seller seller=new Seller();
		seller.setProducts(products);
		String emailId="john@gmail.com";
		Order o=new Order();
		List<Order>orders =new ArrayList<Order>();
		List<Integer> id =new ArrayList<Integer>();
		id.add(p.getProductId());
		
		orders.add(o);
		when(sellerDAO.getSellerByEmailId(emailId)).thenReturn(seller);
	    when(sellerOrderDAO.getOrdersForProducts(id)).thenReturn(orders);
		Assert.assertNotNull(sellerOrderService.viewOrders(emailId));
	}
	@Test
	public void testViewOrdersInValid() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerOrderService.NO_ORDERS_FOR_YOUR_PRODUCTS");
		List<Product> products=new ArrayList<Product>();
		Seller seller=new Seller();
		seller.setProducts(products);
		String emailId="john@gmail.com";
		Mockito.when(sellerDAO.getSellerByEmailId(emailId)).thenReturn(seller);
		Mockito.when(sellerOrderDAO.getOrdersForProducts(new ArrayList<Integer>())).thenReturn(new ArrayList<Order>());
		Assert.assertNotNull(sellerOrderService.viewOrders(emailId));
	}
	
	 

}
