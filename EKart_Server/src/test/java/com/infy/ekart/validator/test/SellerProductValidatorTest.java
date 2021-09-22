package com.infy.ekart.validator.test;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.infy.ekart.model.Product;
import com.infy.ekart.validator.SellerProductValidator;



public class SellerProductValidatorTest {
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	
	@Test
	public void validateProductValidFormat() throws Exception{
		Product product=new Product();
		product.setBrand("Motobot");
		product.setCategory("Electronics - Mobile");
		product.setDescription("Smart phone with (13+13) MP rear camera and 8MP front camera, 4GB RAM and 64GB ROM,5.5 inch FHD display, Snapdrag 625 processor");
		product.setDiscount(5.0);
		product.setName("Xpress");
		product.setPrice(16000.0);
		product.setProductId(1001);
		product.setQuantity(150);
		SellerProductValidator.validateProductForAddNewProduct(product);
	}
	
	@Test
	public void validateProductInvalidProductDescription() throws Exception{
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
		SellerProductValidator.validateProductForAddNewProduct(product);
	}
	
	@Test
	public void validateProductInvalidDiscount() throws Exception{
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
		SellerProductValidator.validateProductForAddNewProduct(product);
	}
	
	@Test
	public void validateProductInvalidDiscount2() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerProductValidator.INVALID_DISCOUNT");
		Product product=new Product();
		product.setBrand("Motobot");
		product.setCategory("Electronics - Mobile");
		product.setDescription("Smart phone with (13+13) MP rear camera and 8MP front camera, 4GB RAM and 64GB ROM,5.5 inch FHD display, Snapdrag 625 processor");
		product.setDiscount(101.0);
		product.setName("Xpress");
		product.setPrice(16000.0);
		product.setProductId(1001);
		product.setQuantity(150);
		SellerProductValidator.validateProductForAddNewProduct(product);
	}
	
	@Test
	public void validateProductInvalidQuantity() throws Exception{
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
		SellerProductValidator.validateProductForAddNewProduct(product);
	}
	
	@Test
	public void validateProductInvalidPrice() throws Exception{
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
		SellerProductValidator.validateProductForAddNewProduct(product);
	}
	
	@Test
	public void validateProductInvalidProductName1() throws Exception{
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
		SellerProductValidator.validateProductForAddNewProduct(product);
	}
	
	@Test
	public void validateProductInvalidProductName2() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerProductValidator.INVALID_NAME");
		Product product=new Product();
		product.setBrand("Motobot");
		product.setCategory("Electronics - Mobile");
		product.setDescription("Smart phone with (13+13) MP rear camera and 8MP front camera, 4GB RAM and 64GB ROM,5.5 inch FHD display, Snapdrag 625 processor");
		product.setDiscount(5.0);
		product.setName("Xpress +");
		product.setPrice(16000.0);
		product.setProductId(1001);
		product.setQuantity(150);
		SellerProductValidator.validateProductForAddNewProduct(product);
	}
	
	@Test
	public void validateProductInvalidProductName3() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerProductValidator.INVALID_NAME");
		Product product=new Product();
		product.setBrand("Motobot");
		product.setCategory("Electronics - Mobile");
		product.setDescription("Smart phone with (13+13) MP rear camera and 8MP front camera, 4GB RAM and 64GB ROM,5.5 inch FHD display, Snapdrag 625 processor");
		product.setDiscount(5.0);
		product.setName("Xpress  ");
		product.setPrice(16000.0);
		product.setProductId(1001);
		product.setQuantity(150);
		SellerProductValidator.validateProductForAddNewProduct(product);
	}
	
	@Test
	public void validateProductInvalidProductName4() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerProductValidator.INVALID_NAME");
		Product product=new Product();
		product.setBrand("Motobot");
		product.setCategory("Electronics - Mobile");
		product.setDescription("Smart phone with (13+13) MP rear camera and 8MP front camera, 4GB RAM and 64GB ROM,5.5 inch FHD display, Snapdrag 625 processor");
		product.setDiscount(5.0);
		product.setName("#Xpress");
    	product.setPrice(16000.0);
		product.setProductId(1001);
		product.setQuantity(150);
		SellerProductValidator.validateProductForAddNewProduct(product);
	}
	
	@Test
	public void validateProductInvalidProductName5() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerProductValidator.INVALID_NAME");
		Product product=new Product();
		product.setBrand("Motobot");
		product.setCategory("Electronics - Mobile");
		product.setDescription("Smart phone with (13+13) MP rear camera and 8MP front camera, 4GB RAM and 64GB ROM,5.5 inch FHD display, Snapdrag 625 processor");
		product.setDiscount(5.0);
		product.setName("  ");
		product.setPrice(16000.0);
		product.setProductId(1001);
		product.setQuantity(150);
		SellerProductValidator.validateProductForAddNewProduct(product);
	}
	
	@Test
	public void validateProductInvalidProductName6() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerProductValidator.INVALID_NAME");
		Product product=new Product();
		product.setBrand("Motobot");
		product.setCategory("Electronics - Mobile");
		product.setDescription("Smart phone with (13+13) MP rear camera and 8MP front camera, 4GB RAM and 64GB ROM,5.5 inch FHD display, Snapdrag 625 processor");
		product.setDiscount(5.0);
		product.setName("");
		product.setPrice(16000.0);
		product.setProductId(1001);
		product.setQuantity(150);
		SellerProductValidator.validateProductForAddNewProduct(product);
	}
	
	@Test
	public void validateProductInvalidProductName7() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerProductValidator.INVALID_NAME");
		Product product=new Product();
		product.setBrand("Motobot");
		product.setCategory("Electronics - Mobile");
		product.setDescription("Smart phone with (13+13) MP rear camera and 8MP front camera, 4GB RAM and 64GB ROM,5.5 inch FHD display, Snapdrag 625 processor");
		product.setDiscount(5.0);
		product.setName(" Xpress  plus");
		product.setPrice(16000.0);
		product.setProductId(1001);
		product.setQuantity(150);
		SellerProductValidator.validateProductForAddNewProduct(product);
	}

}
