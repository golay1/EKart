package com.infy.ekart.validator.test;



import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.infy.ekart.model.Address;
import com.infy.ekart.model.Customer;
import com.infy.ekart.validator.CustomerValidator;

public class CustomerValidatorTest {


	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	
	@Test
	public void validateCustomerValidFormat() throws Exception{
		Customer customer=new Customer();
		customer.setName("Fahad Rahman");
		customer.setEmailId("Fahad@infosys.com");
		customer.setPassword("Fahad@123");
		customer.setPhoneNumber("9988776655");
		CustomerValidator.validateCustomer(customer);
	}
	
	@Test
	public void validateCustomerInvalidEmailId() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerValidator.INVALID_EMAIL_FORMAT");
		Customer customer=new Customer();
		customer.setName("Fahad Rahman");
		customer.setEmailId("Fa@had@infosys.com");
		customer.setPassword("Fahad@123");
		customer.setPhoneNumber("9988776655");
		CustomerValidator.validateCustomer(customer);
	}
	
	@Test
	public void validateCustomerInvalidName1() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerValidator.INVALID_NAME");
		Customer customer=new Customer();
		customer.setName("12FahadRahman");
		customer.setEmailId("Fahad@infosys.com");
		customer.setPassword("Fahad@123");
		customer.setPhoneNumber("9988776655");
		CustomerValidator.validateCustomer(customer);
	}
	
	@Test
	public void validateCustomerInvalidName2() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerValidator.INVALID_NAME");
		Customer customer=new Customer();
		customer.setName("Fahad  Rahman");
		customer.setEmailId("Fahad@infosys.com");
		customer.setPassword("Fahad@123");
		customer.setPhoneNumber("9988776655");
		CustomerValidator.validateCustomer(customer);
	}
	
	@Test
	public void validateCustomerInvalidName3() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerValidator.INVALID_NAME");
		Customer customer=new Customer();
		customer.setName("FahadRahman  ");
		customer.setEmailId("Fahad@infosys.com");
		customer.setPassword("Fahad@123");
		customer.setPhoneNumber("9988776655");
		CustomerValidator.validateCustomer(customer);
	}
	
	@Test
	public void validateCustomerInvalidName4() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerValidator.INVALID_NAME");
		Customer customer=new Customer();
		customer.setName("#FahadRahman");
		customer.setEmailId("Fahad@infosys.com");
		customer.setPassword("Fahad@123");
		customer.setPhoneNumber("9988776655");
		CustomerValidator.validateCustomer(customer);
	}
	
	@Test
	public void validateCustomerInvalidName5() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerValidator.INVALID_NAME");
		Customer customer=new Customer();
		customer.setName("  ");
		customer.setEmailId("Fahad@infosys.com");
		customer.setPassword("Fahad@123");
		customer.setPhoneNumber("9988776655");
		CustomerValidator.validateCustomer(customer);
	}
	
	@Test
	public void validateCustomerInvalidName6() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerValidator.INVALID_NAME");
		Customer customer=new Customer();
		customer.setName("Fahad12Rahman");
		customer.setEmailId("Fahad@infosys.com");
		customer.setPassword("Fahad@123");
		customer.setPhoneNumber("9988776655");
		CustomerValidator.validateCustomer(customer);
	}
	
	@Test
	public void validateCustomerInvalidName7() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerValidator.INVALID_NAME");
		Customer customer=new Customer();
		customer.setName(" Fahad  Rahman");
		customer.setEmailId("Fahad@infosys.com");
		customer.setPassword("Fahad@123");
		customer.setPhoneNumber("9988776655");
		CustomerValidator.validateCustomer(customer);
	}
	
	@Test
	public void validateCustomerInvalidPhoneNumber1() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerValidator.INVALID_PHONE_NUMBER");
		Customer customer=new Customer();
		customer.setName("Fahad Rahman");
		customer.setEmailId("Fahad@infosys.com");
		customer.setPassword("Fahad@123");
		customer.setPhoneNumber("d987Ab321e");
		CustomerValidator.validateCustomer(customer);
	}
	
	@Test
	public void validateCustomerInvalidPhoneNumber2() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerValidator.INVALID_PHONE_NUMBER");
		Customer customer=new Customer();
		customer.setName("Fahad Rahman");
		customer.setEmailId("Fahad@infosys.com");
		customer.setPassword("Fahad@123");
		customer.setPhoneNumber("12345");
		CustomerValidator.validateCustomer(customer);
	}
	
	@Test
	public void validateCustomerInvalidPassword1() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerValidator.INVALID_PASSWORD");
		Customer customer=new Customer();
		customer.setName("Fahad Rahman");
		customer.setEmailId("Fahad@infosys.com");
		customer.setPassword("a123");
		customer.setPhoneNumber("9988776655");
		CustomerValidator.validateCustomer(customer);
	}
	
	@Test
	public void validateCustomerInvalidPassword2() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerValidator.INVALID_PASSWORD");
		Customer customer=new Customer();
		customer.setName("Fahad Rahman");
		customer.setEmailId("Fahad@infosys.com");
		customer.setPassword("ABcd4567894321bdeklmsbx");
		customer.setPhoneNumber("9988776655");
		CustomerValidator.validateCustomer(customer);
	}
	
	@Test
	public void validateCustomerInvalidPassword3() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerValidator.INVALID_PASSWORD");
		Customer customer=new Customer();
		customer.setName("Fahad Rahman");
		customer.setEmailId("Fahad@infosys.com");
		customer.setPassword("jackjill123");
		customer.setPhoneNumber("9988776655");
		CustomerValidator.validateCustomer(customer);
	}
	
	@Test
	public void validateCustomerInvalidPassword4() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerValidator.INVALID_PASSWORD");
		Customer customer=new Customer();
		customer.setName("Fahad Rahman");
		customer.setEmailId("Fahad@infosys.com");
		customer.setPassword("JACKJILL123");
		customer.setPhoneNumber("9988776655");
		CustomerValidator.validateCustomer(customer);
	}
	
	
	@Test
	public void validateCustomerInvalidPassword5() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerValidator.INVALID_PASSWORD");
		Customer customer=new Customer();
		customer.setName("Fahad Rahman");
		customer.setEmailId("Fahad@infosys.com");
		customer.setPassword("45@123456789");
		customer.setPhoneNumber("9988776655");
		CustomerValidator.validateCustomer(customer);
	}
	
	@Test
	public void validateCustomerInvalidPassword6() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerValidator.INVALID_PASSWORD");
		Customer customer=new Customer();
		customer.setName("Fahad Rahman");
		customer.setEmailId("Fahad@infosys.com");
		customer.setPassword("Jack12345");
		customer.setPhoneNumber("9988776655");
		CustomerValidator.validateCustomer(customer);
	}
	
	@Test
	public void validateCustomerInvalidPassword7() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerValidator.INVALID_PASSWORD");
		Customer customer=new Customer();
		customer.setName("Fahad Rahman");
		customer.setEmailId("Fahad@infosys.com");
		customer.setPassword("!@#$%^&*");
		customer.setPhoneNumber("9988776655");
		CustomerValidator.validateCustomer(customer);
	}
	
	@Test
	public void validateCustomerInvalidPassword8() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerValidator.INVALID_PASSWORD");
		Customer customer=new Customer();
		customer.setName("Fahad Rahman");
		customer.setEmailId("Fahad@infosys.com");
		customer.setPassword(" asSDFADfg#$%");
		customer.setPhoneNumber("9988776655");
		CustomerValidator.validateCustomer(customer);
	}
	

	
	@Test
	public void validateCustomerNameValidFormat1() throws Exception{
		Boolean result = CustomerValidator.validateName("Jack Roger");
		Assert.assertTrue(result);
	}
	
	@Test
	public void validateCustomerNameValidFormat2() throws Exception{
		Boolean result = CustomerValidator.validateName("Jack");
		Assert.assertTrue(result);
	}
	
	@Test
	public void validateCustomerNameInvalidFormat1() throws Exception{
		Boolean result = CustomerValidator.validateName(" ");
		Assert.assertFalse(result);
	}
	
	@Test
	public void validateCustomerNameInvalidFormat2() throws Exception{
		Boolean result = CustomerValidator.validateName("1");
		Assert.assertFalse(result);
	}
	
	@Test
	public void validateCustomerNameInvalidFormat3() throws Exception{
		Boolean result = CustomerValidator.validateName("1 Jack ");
		Assert.assertFalse(result);
	}
	
	
	
	
	
	@Test
	public void validateCustomerPhoneNumberValidFormat() throws Exception{
		Boolean result = CustomerValidator.validatePhoneNumber("9876543210");
		Assert.assertTrue(result);
		
	}
	
	
	@Test
	public void validateCustomerPhoneNumberInvalidFormat1() throws Exception{
		Boolean result = CustomerValidator.validatePhoneNumber("987654321");
		Assert.assertFalse(result);
		
	}
	
	
	@Test
	public void validateCustomerPhoneNumberInvalidFormat2() throws Exception{
		Boolean result = CustomerValidator.validatePhoneNumber("A987654321");
		Assert.assertFalse(result);
		
	}

	
	@Test
	public  void validateEmailidValidFormat() throws Exception{
		Boolean result= CustomerValidator.validateEmailId("Tom@infosys.com");
		Assert.assertTrue(result);
	}
	
	@Test
	public  void validateEmailidInValidFormat() throws Exception{
		Boolean result= CustomerValidator.validateEmailId("Tom12@infosys1.c2om");
		Assert.assertFalse(result);
	}
	
	
	
	@Test
	public void validatePasswordInValidFormat1() throws Exception{
		Boolean result=CustomerValidator.validatePassword("Tom@");
		Assert.assertFalse(result);
	}
	
	@Test
	public void validatePasswordInValidFormat2() throws Exception{
		Boolean result=CustomerValidator.validatePassword("Tom@");
		Assert.assertFalse(result);
	}
	
	@Test
	public void validatePasswordInValidFormat8() throws Exception{
		Boolean result=CustomerValidator.validatePassword("45658963214785");
		Assert.assertFalse(result);
	}
	
	@Test
	public void validatePasswordInValidFormat3() throws Exception{
		Boolean result=CustomerValidator.validatePassword("A123456");
		Assert.assertFalse(result);
	}
	@Test
	public void validatePasswordInValidFormat5() throws Exception{
		Boolean result=CustomerValidator.validatePassword("a123456A");
		Assert.assertFalse(result);
	}
	@Test
	public void validatePasswordInValidFormat6() throws Exception{
		Boolean result=CustomerValidator.validatePassword("a123.{jyj");
		Assert.assertFalse(result);
	}

	@Test
	public void validateAddressValidDetails() throws Exception{
		Address address=new Address();
		address.setAddressId(5000);
		address.setAddressLine1("Ist Main, Building No.3");
		address.setAddressLine2("Park Square");
		address.setCity("Los Angeles");
		address.setContactNumber("7886189066");
		address.setState("California");
		address.setPin("752110");
		CustomerValidator.validateAddress(address);
	}
	
	@Test
	public void validateAddressInvalidDetails1() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerValidator.INVALID_ADDRESS_LINE_1");
		Address address=new Address();
		address.setAddressId(5000);
		address.setAddressLine1(" ");
		address.setAddressLine2("Park Square");
		address.setCity("Los Angeles");
		address.setContactNumber("7886189066");
		address.setState("California");
		address.setPin("752110");
		CustomerValidator.validateAddress(address);
	}
	
	@Test
	public void validateAddressInvalidDetails2() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerValidator.INVALID_CITY");
		Address address=new Address();
		address.setAddressId(5000);
		address.setAddressLine1("Ist Main, Building No.3");
		address.setAddressLine2("Park Square");
		address.setCity(" ");
		address.setContactNumber("7886189066");
		address.setState("California");
		address.setPin("752110");
		CustomerValidator.validateAddress(address);
	}
	
	@Test
	public void validateAddressInvalidDetails3() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerValidator.INVALID_STATE");
		Address address=new Address();
		address.setAddressId(5000);
		address.setAddressLine1("Ist Main, Building No.3");
		address.setAddressLine2("Park Square");
		address.setCity("Los Angeles");
		address.setContactNumber("7886189066");
		address.setState(" ");
		address.setPin("752110");
		CustomerValidator.validateAddress(address);
	}
	
	@Test
	public void validateAddressInvalidDetails4() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerValidator.INVALID_CONTACT_NUMBER");
		Address address=new Address();
		address.setAddressId(5000);
		address.setAddressLine1("Ist Main, Building No.3");
		address.setAddressLine2("Park Square");
		address.setCity("Los Angeles");
		address.setContactNumber("788618906");
		address.setState("California");
		address.setPin("752110");
		CustomerValidator.validateAddress(address);
	}
	
	
	@Test
	public void validateAddressInvalidDetails5() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerValidator.INVALID_CONTACT_NUMBER");
		Address address=new Address();
		address.setAddressId(5000);
		address.setAddressLine1("Ist Main, Building No.3");
		address.setAddressLine2("Park Square");
		address.setCity("Los Angeles");
		address.setContactNumber("78861890A6");
		address.setState("California");
		address.setPin("752110");
		CustomerValidator.validateAddress(address);
	}
	
	@Test
	public void validateAddressInvalidDetails6() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerValidator.INVALID_PIN");
		Address address=new Address();
		address.setAddressId(5000);
		address.setAddressLine1("Ist Main, Building No.3");
		address.setAddressLine2("Park Square");
		address.setCity("Los Angeles");
		address.setContactNumber("7886189076");
		address.setState("California");
		address.setPin("7521");
		CustomerValidator.validateAddress(address);
	}
	
	@Test
	public void validateCityValidFormat() throws Exception{
		Boolean result=CustomerValidator.validateCity("Los Angeles");
		Assert.assertTrue(result);
	}
	
	@Test
	public void validateCityInvalidFormat() throws Exception{
		Boolean result=CustomerValidator.validateCity("  ");
		Assert.assertFalse(result);
	}
	
	
	@Test
	public void validateStateValidFormat() throws Exception{
		Boolean result=CustomerValidator.validateState("California");
		Assert.assertTrue(result);
	}
	

	@Test
	public void validateStateInvalidFormat() throws Exception{
		Boolean result=CustomerValidator.validateState("  ");
		Assert.assertFalse(result);
	}
	
	
	@Test
	public void isValidAddressLine1ValidFormat() throws Exception{
		Boolean result=CustomerValidator.isValidAddressLine1("Ist Main, Building No.3");
		Assert.assertTrue(result);
	}
	
	@Test
	public void isValidAddressLine1InvalidFormat() throws Exception{
		Boolean result=CustomerValidator.isValidAddressLine1(" ");
		Assert.assertFalse(result);
	}
	
	@Test
	public void validateContactNumberValidFormat() throws Exception{
		Boolean result=CustomerValidator.validateContactNumber("9956451659");
		Assert.assertTrue(result);
	}
	
	
	@Test
	public void validateContactNumberInvalidFormat1() throws Exception{
		Boolean result=CustomerValidator.validateContactNumber("995645159");
		Assert.assertFalse(result);
	}
	
	@Test
	public void validateContactNumberInvalidFormat2() throws Exception{
		Boolean result=CustomerValidator.validateContactNumber("995A645159");
		Assert.assertFalse(result);
	}
	
	@Test
	public void validatePINValidDetails() throws Exception{
		Boolean result=CustomerValidator.validatePIN("567576");
		Assert.assertTrue(result);
	}
	
	@Test
	public void validatePINInalidDetails() throws Exception{
		Boolean result=CustomerValidator.validatePIN("56776");
		Assert.assertFalse(result);
	}

	@Test 
	public void validateCustomerForUpdateProfileValidFormat() throws Exception{
		Customer customer =new Customer();
		customer.setName("Jack Roger");
		customer.setPhoneNumber("9876543210");
		CustomerValidator.validateCustomerForUpdateProfile(customer);
	}
	
	
	@Test
	public void validateCustomerForUpdateProfileInvalidFormat1() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerValidator.INVALID_PHONE_NUMBER");
		Customer customer =new Customer();
		customer.setName("Jack");
		customer.setPhoneNumber("987654321");
		CustomerValidator.validateCustomerForUpdateProfile(customer);
	}
	
	
	
	
	@Test
	public void validateCustomerForUpdateProfileInvalidFormat2() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerValidator.INVALID_NAME");
		Customer customer =new Customer();
		customer.setName("1Jack");
		customer.setPhoneNumber("9876543210");
		CustomerValidator.validateCustomerForUpdateProfile(customer);
	}
	

	
	
}
