package com.infy.ekart.service.test;

import static org.mockito.ArgumentMatchers.anyString;
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

import com.infy.ekart.dao.CustomerCartDAO;
import com.infy.ekart.model.Customer;
import com.infy.ekart.model.CustomerCart;
import com.infy.ekart.model.Product;
import com.infy.ekart.service.CustomerCartService;
import com.infy.ekart.service.CustomerCartServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerCartServiceTest {
	
	
	@Mock
	private CustomerCartDAO customerCartDAO;
	
	 @InjectMocks
	private CustomerCartService customerCartService = new CustomerCartServiceImpl();
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void getCustomerCartValid() throws Exception{
		Customer customer=new Customer();
		customer.setEmailId("tom@infosys.com");
		List<CustomerCart> list = new ArrayList<CustomerCart>();
		CustomerCart e = null;
		list.add(e);
		when(customerCartDAO.getCustomerCarts(anyString())).thenReturn(list);
		List<CustomerCart> returned=customerCartService.getCustomerCarts(customer.getEmailId());
		Assert.assertNotNull(returned);
		}
	
	@Test
	public void getCustomerCartInValid() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerCartService.NO_PRODUCT_ADDED_TO_CART");
		Customer customer=new Customer();
		customer.setEmailId("tom@infosys.com");
		when(customerCartDAO.getCustomerCarts(anyString())).thenReturn(null);
		customerCartService.getCustomerCarts(customer.getEmailId());
		
	}

	@Test
	public void getCustomerCartInValidFormat() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerCartService.NO_PRODUCT_ADDED_TO_CART");
		Customer customer=new Customer();
		customer.setEmailId("tom@infosys.com");
		when(customerCartDAO.getCustomerCarts(anyString())).thenReturn(new ArrayList<CustomerCart>());
		customerCartService.getCustomerCarts(customer.getEmailId());
		
	}
	@Test
	public void testaddProductToCartValid() throws Exception{
		Product p=new Product();
		p.setQuantity(4);
		p.setProductId(112);
		CustomerCart cart=new CustomerCart();
		cart.setProduct(p);
		cart.setQuantity(1);
		Product p1=new Product();
		p1.setProductId(222);
		CustomerCart cart1=new CustomerCart();
		cart1.setProduct(p1);
		List<CustomerCart> customerCart=new ArrayList<CustomerCart>();
		customerCart.add(cart1);
		
		when(customerCartDAO.getCustomerCarts(Mockito.anyString())).thenReturn(customerCart);
		when(customerCartDAO.getProductById(cart.getProduct().getProductId())).thenReturn(p);
		Mockito.doNothing().when(customerCartDAO).addProductToCart(Mockito.anyString(), Mockito.any(CustomerCart.class));
		
		customerCartService.addProductToCart("abcd@infosys.com", cart);
			}
	@Test
	public void testaddProductToCartInValid() throws Exception{
		Product p=new Product();
		p.setQuantity(4);
		p.setProductId(112);
		CustomerCart cart=new CustomerCart();
		cart.setProduct(p);
		cart.setQuantity(1);
		List<CustomerCart> customerCart=new ArrayList<CustomerCart>();
		customerCart.add(cart);
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerCartService.PRODUCT_PRESENT_IN_CART");
		
		when(customerCartDAO.getCustomerCarts(Mockito.anyString())).thenReturn(customerCart);
		when(customerCartDAO.getProductById(cart.getProduct().getProductId())).thenReturn(p);
		Mockito.doNothing().when(customerCartDAO).addProductToCart(Mockito.anyString(), Mockito.any(CustomerCart.class));
		
		customerCartService.addProductToCart("abcd@infosys.com", cart);
			}
	@Test
	public void testaddProductToCartInValidSufficientStock() throws Exception{
		Product p=new Product();
		p.setQuantity(1);
		p.setProductId(112);
		CustomerCart cart=new CustomerCart();
		cart.setProduct(p);
		cart.setQuantity(3);
	
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerCartService.INSUFFICIENT_STOCK");
		
		when(customerCartDAO.getCustomerCarts(Mockito.anyString())).thenReturn(new ArrayList<CustomerCart>());
		when(customerCartDAO.getProductById(cart.getProduct().getProductId())).thenReturn(p);
		Mockito.doNothing().when(customerCartDAO).addProductToCart(Mockito.anyString(), Mockito.any(CustomerCart.class));
		
		customerCartService.addProductToCart("abcd@infosys.com", cart);
			}
	
	@Test public void testDeleteProductFromCartValid() throws Exception{
		Mockito.doNothing().when(customerCartDAO).deleteProductFromCart(Mockito.anyString(), Mockito.anyInt());
		customerCartService.deleteProductFromCart("abcd@infy.com", 1);
	}
	
	@Test public void testModifyQuantityToCartValid() throws Exception{
		Product p=new Product();
		p.setQuantity(3);
		when(customerCartDAO.getProductById(3)).thenReturn(p);
		Mockito.doNothing().when(customerCartDAO).modifyQuantityOfProductInCart(Mockito.anyInt(),Mockito.anyInt());
		customerCartService.modifyQuantityOfProductInCart(1, 2, 3);
	}
	
	@Test public void testModifyQuantityToCartInValid() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerCartService.INSUFFICIENT_STOCK");
		Product p=new Product();
		p.setQuantity(1);
		when(customerCartDAO.getProductById(3)).thenReturn(p);
		Mockito.doNothing().when(customerCartDAO).modifyQuantityOfProductInCart(Mockito.anyInt(),Mockito.anyInt());
		customerCartService.modifyQuantityOfProductInCart(1, 2, 3);
	}
}
