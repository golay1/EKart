package com.infy.ekart.service.test;

import java.util.ArrayList;

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

import com.infy.ekart.dao.SellerProductDAO;
import com.infy.ekart.model.Product;
import com.infy.ekart.service.SellerProductService;
import com.infy.ekart.service.SellerProductServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerProductServiceTest {
	
	@Mock
	private SellerProductDAO sellerProductDAO;
	
	@InjectMocks
	private SellerProductService sellerProductService=new SellerProductServiceImpl();
	
	@Rule
	public ExpectedException expectedException=ExpectedException.none();
	
	@Test
	public void testAddNewProduct() throws Exception{
		Product product=new Product();
		product.setBrand("Motobot");
		product.setCategory("Electronics - Mobile");
		product.setDescription("Smart phone with (13+13) MP rear camera and 8MP front camera, 4GB RAM and 64GB ROM,5.5 inch FHD display, Snapdrag 625 processor");
		product.setDiscount(5.0);
		product.setName("Xpress");
		product.setPrice(16000.0);
		product.setProductId(1001);
		product.setQuantity(150);
//		Mockito.when(sellerProductDAO.addNewProduct(product,"")).thenReturn(1);
//		Assert.assertNotNull(sellerProductService.addNewProduct(product, ""));
	}
	@Test
	public void testAddNewProductInvalidDescription() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerProductValidator.INVALID_DESCRIPTION");
		Product product=new Product();
		product.setBrand("Motobot");
		product.setCategory("Electronics - Mobile");
		product.setDescription(" ");
		product.setDiscount(5.0);
		product.setName("Xpress");
		product.setPrice(16000.0);
		product.setProductId(1001);
		product.setQuantity(150);
		sellerProductService.addNewProduct(product);
//		Assert.assertNotNull(sellerProductService.addNewProduct(product, ""));
	}

	@Test
	public void testAddNewProductInvalidDiscount() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerProductValidator.INVALID_DISCOUNT");
		Product product=new Product();
		product.setBrand("Motobot");
		product.setCategory("Electronics - Mobile");
		product.setDescription("Smart phone with (13+13) MP rear camera and 8MP front camera, 4GB RAM and 64GB ROM,5.5 inch FHD display, Snapdrag 625 processor");
		product.setDiscount(-10.0);
		product.setName("Xpress");
		product.setPrice(16000.0);
		product.setProductId(1001);
		product.setQuantity(150);
		sellerProductService.addNewProduct(product);
//		Assert.assertNotNull(sellerProductService.addNewProduct(product, ""));
	}
	@Test
	public void testAddNewProductInvalidQuantity() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerProductValidator.INVALID_QUANTITY");
		Product product=new Product();
		product.setBrand("Motobot");
		product.setCategory("Electronics - Mobile");
		product.setDescription("Smart phone with (13+13) MP rear camera and 8MP front camera, 4GB RAM and 64GB ROM,5.5 inch FHD display, Snapdrag 625 processor");
		product.setDiscount(5.0);
		product.setName("Xpress");
		product.setPrice(16000.0);
		product.setProductId(1001);
		product.setQuantity(0);
		sellerProductService.addNewProduct(product);
//		Assert.assertNotNull(sellerProductService.addNewProduct(product, ""));
	}

	@Test
	public void testAddNewProductInvalidPrice() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerProductValidator.INVALID_PRICE");
		Product product=new Product();
		product.setBrand("Motobot");
		product.setCategory("Electronics - Mobile");
		product.setDescription("Smart phone with (13+13) MP rear camera and 8MP front camera, 4GB RAM and 64GB ROM,5.5 inch FHD display, Snapdrag 625 processor");
		product.setDiscount(5.0);
		product.setName("Xpress");
		product.setPrice(0.0);
		product.setProductId(1001);
		product.setQuantity(150);
		sellerProductService.addNewProduct(product);
//		Assert.assertNotNull(sellerProductService.addNewProduct(product, ""));
	}

	@Test
	public void testAddNewProductInvalidProductName() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerProductValidator.INVALID_NAME");
		Product product=new Product();
		product.setBrand("Motobot");
		product.setCategory("Electronics - Mobile");
		product.setDescription("Smart phone with (13+13) MP rear camera and 8MP front camera, 4GB RAM and 64GB ROM,5.5 inch FHD display, Snapdrag 625 processor");
		product.setDiscount(5.0);
		product.setName("12Xpress ");
		product.setPrice(16000.0);
		product.setProductId(1001);
		product.setQuantity(150);
		sellerProductService.addNewProduct(product);
//		Assert.assertNotNull(sellerProductService.addNewProduct(product, ""));
	}
	
	@Test
	public void testGetProductCategoryList() throws Exception{
		Mockito.when(sellerProductDAO.getProductCategoryList()).thenReturn(new ArrayList<String>());
		Assert.assertNotNull(sellerProductService.getProductCategoryList());
	}
	
	@Test
	public void testModifyProductDetails() throws Exception{
		Product product=new Product();
		product.setBrand("Motobot");
		product.setCategory("Electronics - Mobile");
		product.setDescription("Smart phone with (13+13) MP rear camera and 8MP front camera, 4GB RAM and 64GB ROM,5.5 inch FHD display, Snapdrag 625 processor");
		product.setDiscount(5.0);
		product.setName("Xpress");
		product.setPrice(16000.0);
		product.setProductId(1001);
		product.setQuantity(150);
//		Mockito.when(sellerProductDAO.modifyProductDetails(product,"")).thenReturn(new Product());
//		Assert.assertNotNull(sellerProductService.modifyProductDetails(product, ""));
		
	}
	@Test
	public void testModifyProductDetailsInvalidProductName() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerProductValidator.INVALID_NAME");
		Product product=new Product();
		product.setBrand("Motobot");
		product.setCategory("Electronics - Mobile");
		product.setDescription("Smart phone with (13+13) MP rear camera and 8MP front camera, 4GB RAM and 64GB ROM,5.5 inch FHD display, Snapdrag 625 processor");
		product.setDiscount(5.0);
		product.setName("12Xpress ");
		product.setPrice(16000.0);
		product.setProductId(1001);
		product.setQuantity(150);
		sellerProductService.modifyProductDetails(product);
//		Assert.assertNotNull(sellerProductService.modifyProductDetails(product, ""));
		
	}
	@Test
	public void testModifyProductDetailsInvalidPrice() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerProductValidator.INVALID_PRICE");
		Product product=new Product();
		product.setBrand("Motobot");
		product.setCategory("Electronics - Mobile");
		product.setDescription("Smart phone with (13+13) MP rear camera and 8MP front camera, 4GB RAM and 64GB ROM,5.5 inch FHD display, Snapdrag 625 processor");
		product.setDiscount(5.0);
		product.setName("Xpress");
		product.setPrice(0.0);
		product.setProductId(1001);
		product.setQuantity(150);
		sellerProductService.modifyProductDetails(product);
//		Assert.assertNotNull(sellerProductService.modifyProductDetails(product, ""));
		
	}
	@Test
	public void testModifyProductDetailsInvalidQuantity() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerProductValidator.INVALID_QUANTITY");
		Product product=new Product();
		product.setBrand("Motobot");
		product.setCategory("Electronics - Mobile");
		product.setDescription("Smart phone with (13+13) MP rear camera and 8MP front camera, 4GB RAM and 64GB ROM,5.5 inch FHD display, Snapdrag 625 processor");
		product.setDiscount(5.0);
		product.setName("Xpress");
		product.setPrice(16000.0);
		product.setProductId(1001);
		product.setQuantity(0);
		sellerProductService.modifyProductDetails(product);
//		Assert.assertNotNull(sellerProductService.modifyProductDetails(product, ""));
		
	}
	@Test
	public void testModifyProductDetailsInvalidDiscount() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerProductValidator.INVALID_DISCOUNT");
		Product product=new Product();
		product.setBrand("Motobot");
		product.setCategory("Electronics - Mobile");
		product.setDescription("Smart phone with (13+13) MP rear camera and 8MP front camera, 4GB RAM and 64GB ROM,5.5 inch FHD display, Snapdrag 625 processor");
		product.setDiscount(-10.0);
		product.setName("Xpress");
		product.setPrice(16000.0);
		product.setProductId(1001);
		product.setQuantity(150);
		sellerProductService.modifyProductDetails(product);
//		Assert.assertNotNull(sellerProductService.modifyProductDetails(product, ""));
		
	}
	@Test
	public void testModifyProductDetailsInvalidDescription() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerProductValidator.INVALID_DESCRIPTION");
		Product product=new Product();
		product.setBrand("Motobot");
		product.setCategory("Electronics - Mobile");
		product.setDescription(" ");
		product.setDiscount(5.0);
		product.setName("Xpress");
		product.setPrice(16000.0);
		product.setProductId(1001);
		product.setQuantity(150);
		sellerProductService.modifyProductDetails(product);
//		Assert.assertNotNull(sellerProductService.modifyProductDetails(product, ""));
		
	}

	@Test
	public void testRemoveProduct() throws Exception{
//		Mockito.when(sellerProductDAO.removeProduct(Mockito.anyInt(),Mockito.anyString())).thenReturn(1);
//		Assert.assertNotNull(sellerProductService.removeProduct(1, ""));
	}

}
