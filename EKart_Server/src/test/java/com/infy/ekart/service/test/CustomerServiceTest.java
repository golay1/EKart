package com.infy.ekart.service.test;

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

import com.infy.ekart.dao.CustomerDAO;
import com.infy.ekart.model.Address;
import com.infy.ekart.model.Customer;
import com.infy.ekart.service.CustomerService;
import com.infy.ekart.service.CustomerServiceImpl;
import com.infy.ekart.utility.HashingUtility;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {
	

	@Mock
	private CustomerDAO customerDAO;
	
	@InjectMocks
	private CustomerService customerService=new CustomerServiceImpl();
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	
	@Test
	public void authenticateCustomerInValidDetails() throws Exception {
		
	
		String password = "Tom@123";
		Mockito.when(customerDAO.getPasswordOfCustomer(Mockito.anyString())).thenReturn(null);
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerService.INVALID_CREDENTIALS");
		customerService.authenticateCustomer("Toinfosys.com", password);
		
	}
	
	
	@Test
	public void authenticateCustomerInValidDetails1() throws Exception {
		
		String password = "Tom23";
		String hashPassword = HashingUtility.getHashValue(password)+" ";
		Mockito.when(customerDAO.getPasswordOfCustomer(Mockito.anyString())).thenReturn(hashPassword);
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerService.INVALID_CREDENTIALS");
		customerService.authenticateCustomer("Tom@infosys.com", password);
		
	}
	
	@Test
	public void authenticateCustomerInValidDetails2() throws Exception {
		

		String password = "Tom23";
		Mockito.when(customerDAO.getPasswordOfCustomer(Mockito.anyString())).thenReturn(null);
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerService.INVALID_CREDENTIALS");
		customerService.authenticateCustomer("Tom@infosys.com", password);
		
	}
	
	@Test
	public void addShippingAddressValid() throws Exception{
		Address address=new Address();
		address.setAddressId(5000);
		address.setAddressLine1("Ist Main, Building No.3");
		address.setAddressLine2("Park Square");
		address.setCity("Los Angeles");
		address.setContactNumber("7886189066");
		address.setState("California");
		address.setPin("752110");
		Mockito.when(customerDAO.addShippingAddress("",address)).thenReturn(1);
		Assert.assertNotNull(customerService.addShippingAddress(" ", address));
	}
	
	@Test
	public void addShippingAddressInValidCity() throws Exception{
		Address address=new Address();
		address.setAddressId(5000);
		address.setAddressLine1("Ist Main, Building No.3");
		address.setAddressLine2("Park Square");
		address.setCity(" ");
		address.setContactNumber("7886189066");
		address.setState("California");
		address.setPin("752110");
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerValidator.INVALID_CITY");
		
		Mockito.when(customerDAO.addShippingAddress("",address)).thenReturn(null);
		customerService.addShippingAddress(" ", address);
	} 
	
	@Test
	public void addShippingAddressInValidAddress() throws Exception{
		Address address=new Address();
		address.setAddressId(5000);
		address.setAddressLine1("");
		address.setAddressLine2("Park Square");
		address.setCity("Vegas");
		address.setContactNumber("7886189066");
		address.setState("California");
		address.setPin("752110");
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerValidator.INVALID_ADDRESS_LINE_1");
		
		Mockito.when(customerDAO.addShippingAddress("",address)).thenReturn(null);
		customerService.addShippingAddress(" ", address);
	} 
	
	
	@Test
	public void addShippingAddressInValidContact() throws Exception{
		Address address=new Address();
		address.setAddressId(5000);
		address.setAddressLine1("Ist Main,Door no.333");
		address.setAddressLine2("Park Square");
		address.setCity("Vegas");
		address.setContactNumber("7886dd189066");
		address.setState("California");
		address.setPin("752110");
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerValidator.INVALID_CONTACT_NUMBER");
		
		Mockito.when(customerDAO.addShippingAddress("",address)).thenReturn(null);
		customerService.addShippingAddress(" ", address);
	} 
	
	@Test
	public void testChangePasswordInValidnewPassword() throws Exception{
		String newPassword="tim123#";
		String currentPassword="Tim!123";
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerService.INVALID_NEW_PASSWORD");
		
		customerService.changePassword("",currentPassword,newPassword);
		
	}
	@Test
	public void testChangePasswordInValidCurrentPassword() throws Exception{
		String newPassword="Tim123#";
		String currentPassword="Tim!123";
		String hashCurrentPassword = HashingUtility.getHashValue(currentPassword+" g");
		String hashNewPassword = HashingUtility.getHashValue(newPassword);
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerService.INVALID_CURRENT_PASSWORD");
		
		Mockito.when(customerDAO.getPasswordOfCustomer(Mockito.anyString())).thenReturn(hashCurrentPassword);
		Mockito.doNothing().when(customerDAO).changePassword("", hashNewPassword);
		customerService.changePassword(Mockito.anyString(),currentPassword,newPassword);
		
	}
	@Test
	public void testDeleteShippingAddress() throws Exception{
		Mockito.doNothing().when(customerDAO).deleteShippingAddress(Mockito.anyString(), Mockito.anyInt());
		customerService.deleteShippingAddress("",10);
		
	}
	@Test
	public void testRegisterNewCustomer() throws Exception{
		Customer customer=new Customer();
		customer.setName("Fahad Rahman");
		customer.setEmailId("Fahad@infosys.com");
		customer.setPassword("Fahad@123");
		customer.setPhoneNumber("9988776655");
		Mockito.when(customerDAO.checkAvailabilityOfEmailId(Mockito.anyString())).thenReturn(true);
		Mockito.when(customerDAO.checkAvailabilityOfPhoneNumber(Mockito.anyString())).thenReturn(true);
		Mockito.when(customerDAO.registerNewCustomer(customer)).thenReturn("1");
		Assert.assertNotNull(customerService.registerNewCustomer(customer));
	}
	@Test
	public void testRegisterNewCustomerPhoneInUse() throws Exception{
		Customer customer=new Customer();
		customer.setName("Fahad Rahman");
		customer.setEmailId("Fahad@infosys.com");
		customer.setPassword("Fahad@123");
		customer.setPhoneNumber("9988776655");
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerService.PHONE_NUMBER_ALREADY_IN_USE");
		
		Mockito.when(customerDAO.checkAvailabilityOfEmailId(Mockito.anyString())).thenReturn(true);
		Mockito.when(customerDAO.checkAvailabilityOfPhoneNumber(Mockito.anyString())).thenReturn(false);
		Mockito.when(customerDAO.registerNewCustomer(customer)).thenReturn("1");
		Assert.assertNotNull(customerService.registerNewCustomer(customer));
	}	
	@Test
	public void registerNewCustomerExistingEmailID() throws Exception {
		
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerService.EMAIL_ID_ALREADY_IN_USE");
		
		Customer customer=new Customer();
		customer.setEmailId("Fahad@infosys.com");
		customer.setName("Fahad Rahman");
		customer.setPhoneNumber("9988776655");
		customer.setPassword("Fahad@123");
		Assert.assertNotNull(customerService.registerNewCustomer(customer));
	}
	
	@Test
	public void testRegisterNewCustomerInValidEmail() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerValidator.INVALID_EMAIL_FORMAT");
		Customer customer=new Customer();
		customer.setName("Fahad Rahman");
		customer.setEmailId("Fa@had@infosys.com");
		customer.setPassword("Fahad@123");
		customer.setPhoneNumber("9988776655");
		Assert.assertNotNull(customerService.registerNewCustomer(customer));
	}
	@Test
	public void testRegisterNewCustomerInValidName() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerValidator.INVALID_NAME");
		Customer customer=new Customer();
		customer.setName("12FahadRahman");
		customer.setEmailId("Fahad@infosys.com");
		customer.setPassword("Fahad@123");
		customer.setPhoneNumber("9988776655");
		Assert.assertNotNull(customerService.registerNewCustomer(customer));
	}
	@Test
	public void testRegisterNewCustomerInValidPassword() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerValidator.INVALID_PASSWORD");
		Customer customer=new Customer();
		customer.setName("Fahad Rahman");
		customer.setEmailId("Fahad@infosys.com");
		customer.setPassword("a123");
		customer.setPhoneNumber("9988776655");
		Assert.assertNotNull(customerService.registerNewCustomer(customer));
	}
	
	@Test
	public void testRegisterNewCustomerInValidPhoneNO() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerValidator.INVALID_PHONE_NUMBER");
		Customer customer=new Customer();
		customer.setName("Fahad Rahman");
		customer.setEmailId("Fahad@infosys.com");
		customer.setPassword("Fahad@123");
		customer.setPhoneNumber("d987Ab321e");
		Assert.assertNotNull(customerService.registerNewCustomer(customer));
	}
	
	@Test
	public void testUpdateCustomer() throws Exception{
		Customer customer =new Customer();
		customer.setName("Jack Roger");
		customer.setPhoneNumber("9876543210");
		Mockito.when(customerDAO.getCustomerByPhoneNumber(customer.getPhoneNumber())).thenReturn(null);
		Mockito.doNothing().when(customerDAO).updateProfile(customer);
		customerService.updateProfile(customer);
	}
	
	@Test
	public void testUpdateCustomerValid() throws Exception{
		Customer customer =new Customer();
		customer.setName("Jack Roger");
		customer.setPhoneNumber("9876543210");
		customer.setEmailId("jack@gmail.com");
		Customer newCustomer =new Customer();
		newCustomer.setEmailId("jack@gmail.com");
		Mockito.when(customerDAO.getCustomerByPhoneNumber(customer.getPhoneNumber())).thenReturn(newCustomer);
		Mockito.doNothing().when(customerDAO).updateProfile(customer);
		customerService.updateProfile(customer);
	}
	@Test
	public void testUpdateCustomerInValid() throws Exception{
		Customer customer =new Customer();
		customer.setName("Jack Roger");
		customer.setPhoneNumber("9876543210");
		customer.setEmailId("jack@gmail.com");
		Customer newCustomer =new Customer();
		newCustomer.setEmailId("jack@g.com");
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerService.PHONE_NUMBER_ALREADY_IN_USE");
		
		Mockito.when(customerDAO.getCustomerByPhoneNumber(customer.getPhoneNumber())).thenReturn(newCustomer);
		Mockito.doNothing().when(customerDAO).updateProfile(customer);
		customerService.updateProfile(customer);
	}
	
	@Test
	public void testUpdateShippingAddress() throws Exception{
		Address address=new Address();
		address.setAddressId(5000);
		address.setAddressLine1("Ist Main, Building No.3");
		address.setAddressLine2("Park Square");
		address.setCity("Los Angeles");
		address.setContactNumber("7886189066");
		address.setState("California");
		address.setPin("752110");
		Mockito.doNothing().when(customerDAO).updateShippingAddress(address);
		customerService.updateShippingAddress(address);
		
	}
}
