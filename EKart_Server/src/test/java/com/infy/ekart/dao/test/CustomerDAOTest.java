package com.infy.ekart.dao.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.infy.ekart.dao.CustomerDAO;
import com.infy.ekart.model.Address;
import com.infy.ekart.model.Customer;
import com.infy.ekart.utility.HashingUtility;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@Rollback(true)
public class CustomerDAOTest {
	@Autowired
	private CustomerDAO customerDAO;
	
	
	
	@Test
	public void checkAvailabilityOfPhoneNumberValid() {
		Customer customer=new Customer();
		customer.setPhoneNumber("9988776655");
		customerDAO.checkAvailabilityOfPhoneNumber(customer.getPhoneNumber());
		Assert.assertTrue(true);
		
	}
	
	@Test
	public void checkAvailabilityOfPhoneNumberInValid() {
		Customer customer=new Customer();
		customer.setPhoneNumber("123ab45678");
		customerDAO.checkAvailabilityOfPhoneNumber(customer.getPhoneNumber());
		Assert.assertFalse(false);
		
	}
	
	@Test
	public void checkAvailabilityOfEmailIdValid(){
		Customer customer=new Customer();
		customer.setEmailId("Fahad@infosys.com");
		customerDAO.checkAvailabilityOfEmailId(customer.getEmailId());
		Assert.assertTrue(true);
	}
	
	@Test
	public void checkAvailabilityOfEmailIdInValid(){
		Customer customer=new Customer();
		customer.setEmailId("Fa@had1992@infosys.com");
		customerDAO.checkAvailabilityOfEmailId(customer.getEmailId());
		Assert.assertFalse(false);
	}
	
	@Test 
	public void registerNewCustomerValidDetails() {
		Customer customer=new Customer();
		customer.setEmailId("Fahad@infosys.com");
		customer.setPassword("Fahad@123");
		customer.setName("Fahad Rahman");
		customer.setPhoneNumber("9988776655");
	
		customerDAO.registerNewCustomer(customer);
		Assert.assertTrue(true);
	}
	
	@Test 
	public void registerNewCustomerInValidDetails() {
		Customer customer=new Customer();
		customer.setEmailId("Fahad@infosys.com");
		customer.setPassword("Fahad@123");
		customer.setName("Fahad  rahman");
		customer.setPhoneNumber("9988776655");
	
		customerDAO.registerNewCustomer(customer);
		Assert.assertFalse(false);
	}
	
	@Test 
	public void updateCustomerValidDetails() {
		Customer customer=new Customer();
		customer=customerDAO.getCustomerByEmailId("mariya@infosys.com");
		customer.setName("Mariya M");
		customer.setPhoneNumber("7578116427");
	
		customerDAO.updateProfile(customer);
		Assert.assertTrue(true);
	}
	
	
	
	@Test
	public void changePasswordValidPassword() throws Exception{
		Customer customer = new Customer();
		String newHashedPassword=HashingUtility.getHashValue("JackP@123");
		customer.setPassword(newHashedPassword);
		customer.setEmailId("tom@infosys.com");
		customerDAO.changePassword(customer.getEmailId(), customer.getPassword());
		Assert.assertTrue(true);
	}
	
	@Test
	public void changePasswordInValidPassword() throws Exception{
		Customer customer = new Customer();
		String newHashedPassword=HashingUtility.getHashValue("J@123");
		customer.setPassword(newHashedPassword);
		customer.setEmailId("tom@infosys.com");
		customerDAO.changePassword(customer.getEmailId(), customer.getPassword());
		Assert.assertFalse(false);
	}
	
	
	@Test
	public void addShippingAddressValidDetails() throws Exception{
		Address address=new Address();
		address.setAddressId(5000);
		address.setAddressLine1("Ist Main, Building No.3");
		address.setAddressLine2("Park Square");
		address.setCity("Los Angeles");
		address.setContactNumber("7886189066");
		address.setState("California");
		address.setPin("752110");
		customerDAO.addShippingAddress("tom@infosys.com", address);
		
	}
	
	
	@Test
	public void getCustomerByPhoneNumberValidDetails() throws Exception{
		customerDAO.getCustomerByPhoneNumber("7886189067");
	}
	
	@Test
	public void getCustomerByPhoneNoInvalidDetails() throws Exception{
		customerDAO.getCustomerByPhoneNumber("7886189066");
	}
	
	@Test
	public void deleteShippingAddressValidDetails() throws Exception{
		customerDAO.deleteShippingAddress("tom@infosys.com", 5000);
	}
	
	@Test
	public void updateShippingAddressValidDetails() throws Exception{
		Address address=new Address();
		address.setAddressId(5000);
		address.setAddressLine1("Ist Main, Building No.3");
		address.setAddressLine2("Park Square");
		address.setCity("Los Angeles");
		address.setContactNumber("7886189089");
		address.setState("California");
		address.setPin("752110");
		customerDAO.updateShippingAddress(address);
		
	}
	
	@Test
	public void getPasswordofCustomerValidDetails() {
		customerDAO.getPasswordOfCustomer("Tom@infosys.com");
		
	}
	
	
	
	@Test
	public void getCustomerbyEmailIdValidDetails() {
		customerDAO.getCustomerByEmailId("Tom@infosys.com");
	}
	@Test
	public void getCustomerbyEmailIdInValidDetails() {
		customerDAO.getCustomerByEmailId("T12");
	}

}
