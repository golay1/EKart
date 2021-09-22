package com.infy.ekart.dao.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.infy.ekart.dao.SellerDAO;
import com.infy.ekart.model.Seller;
import com.infy.ekart.utility.HashingUtility;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@Rollback(true)
public class SellerDAOTest {
	@Autowired
	private SellerDAO sellerDAO;

	@Test
	public void getPasswordOfSellerValidDetails(){
		sellerDAO.getPasswordOfSeller("mark@infosys.com");
		Assert.assertTrue(true);
	}
	
	@Test
	public void getPasswordOfSellerInValidDetails(){
		sellerDAO.getPasswordOfSeller("123");
		Assert.assertFalse(false);
	}
	
	@Test
	public void checkRegisteredPhoneNumberValid() {
		Seller seller = new Seller();
		seller.setPhoneNumber("6252123355");
		sellerDAO.checkAvailabilityOfPhoneNumber(seller.getPhoneNumber());
		Assert.assertTrue(true);
		
	}
	
	@Test
	public void checkRegisteredPhoneNumberInvalid() {
		Seller seller = new Seller();
		seller.setPhoneNumber("123ab45678");
		sellerDAO.checkAvailabilityOfPhoneNumber(seller.getPhoneNumber());
		Assert.assertFalse(false);
		
	}
	
	
	@Test
	public void checkAvailabilityOfEmailIdValid(){
		Seller seller = new Seller();
		seller.setEmailId("mark@infosys.com");
		sellerDAO.checkAvailabilityOfEmailId(seller.getEmailId());
		
		Assert.assertTrue(true);
	}
	
	@Test
	public void checkAvailabilityOfEmailIdInValid(){
		
		Seller seller = new Seller();
		seller.setEmailId("ma@rk1992@infosys.com");
		sellerDAO.checkAvailabilityOfEmailId(seller.getEmailId());
		
		Assert.assertFalse(false);
	}
	
	@Test 
	public void registerNewSellerValidDetails() {
		Seller seller = new Seller();
		seller.setEmailId("Jerry1992@infosys.com");
		seller.setAddress("2nd main, 8th cross, Park square, NYC-332290");
		seller.setName("Jerry Abrahm");
		seller.setPhoneNumber("9234567890");
		
		sellerDAO.registerNewSeller(seller);
		Assert.assertTrue(true);
	}
	
	@Test 
	public void registerNewSellerInValidDetails() {
		Seller seller = new Seller();
		seller.setEmailId("Je@rry1992@infosys.com");
		seller.setAddress("2nd main, 8th cross, Park square, NYC-332290");
		seller.setName("Jerry Abrahm");
		seller.setPhoneNumber("9234567890");
		
		sellerDAO.registerNewSeller(seller);
		Assert.assertFalse(false);
	}
	
	@Test 
	public void updateSellerValidDetails() {
		Seller seller = new Seller();
		seller.setEmailId("jack@infosys.com");
		seller.setName("Jack M");
		seller.setPhoneNumber("7614162396");
		seller.setAddress("Ist Main, Building No.3, Park Square, Salem, US");
	
		sellerDAO.updateProfile(seller);
		Assert.assertTrue(true);
	}

	@Test
	public void getSellerByEmailIdValidDetails() throws Exception{
		sellerDAO.getSellerByEmailId("jack@infosys.com");
		Assert.assertTrue(true);
	}
	
	@Test
	public void getSellerByPhoneNumberValidDetails() throws Exception{
		sellerDAO.getSellerByPhoneNumber("7299115216");
		Assert.assertTrue(true);
	}
	
	@Test
	public void getSellerByPhoneNumberInvalidDetails() throws Exception{
		sellerDAO.getSellerByPhoneNumber("72ab115217");
		Assert.assertFalse(false);
	}
	
	@Test
	public void changePasswordValidPassword() throws Exception{
		Seller seller = new Seller();
		String newHashedPassword=HashingUtility.getHashValue("JackP@123");
		seller.setPassword(newHashedPassword);
		seller.setEmailId("jack@infosys.com");
		
		sellerDAO.changePassword(seller.getEmailId(), seller.getPassword());
		Assert.assertTrue(true);
	}
	
	@Test
	public void changePasswordInValidPassword() throws Exception{
		Seller seller = new Seller();
		String newHashedPassword=HashingUtility.getHashValue("J@123");
		seller.setPassword(newHashedPassword);
		seller.setEmailId("jack@infosys.com");
		
		sellerDAO.changePassword(seller.getEmailId(), seller.getPassword());
		Assert.assertFalse(false);
	}
	
}
