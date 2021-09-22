package com.infy.ekart.validator.test;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.infy.ekart.model.Seller;
import com.infy.ekart.validator.SellerValidator;

public class SellerValidatorTest {
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public  void validateEmailidValidFormat() throws Exception{
	Boolean result= SellerValidator.validateEmailId("Jack@infosys.com");
	Assert.assertTrue(result);
}

	@Test
	public  void validateEmailidInValidFormat() throws Exception{
		
		Boolean result= SellerValidator.validateEmailId("Tom12@infosys1.c2om");
		Assert.assertFalse(result);
}	
	@Test
	public void validatePasswordValidFormat() throws Exception{
		Boolean result=SellerValidator.validatePassword("Jack@123");
		Assert.assertTrue(result);
	}
	
	@Test
	public void validatePasswordInValidFormat() throws Exception{
		Boolean result=SellerValidator.validatePassword("Tom@");
		Assert.assertFalse(result);
	}
	
	@Test
	public void validatePasswordInValidFormat1() throws Exception{
		Boolean result=SellerValidator.validatePassword("A123456");
		Assert.assertFalse(result);
	}
	@Test
	public void validatePasswordInValidFormat2() throws Exception{
		Boolean result=SellerValidator.validatePassword("a123456");
		Assert.assertFalse(result);
	}
	@Test
	public void validatePasswordInValidFormat3() throws Exception{
		Boolean result=SellerValidator.validatePassword("a123456A");
		Assert.assertFalse(result);
	}
	@Test
	public void validatePasswordInValidFormat4() throws Exception{
		Boolean result=SellerValidator.validatePassword("45658963214785");
		Assert.assertFalse(result);
	}
	
	@Test
	public void validateSellerLoginValidFormat() throws Exception{
		SellerValidator.validateSellerForLogin("Jack@infosys.com", "Jack@123");
		Assert.assertTrue(true);
		
	}
	@Test
	public void validateSellerLoginInValidFormat() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerValidator.INVALID_EMAIL_FORMAT");
		SellerValidator.validateSellerForLogin("Jackinfosys.com", "Jack@123");
		Assert.assertFalse(false);
		
	}
	@Test
	public void validateSellerLoginInValidFormat1() throws Exception{
	SellerValidator.validateSellerForLogin("Jack@infosys.com", "Jack@23");
		Assert.assertFalse(false);
		
	}
	@Test
	public void validateSellerLoginInValidFormat2() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerValidator.INVALID_PASSWORD_FORMAT");
		SellerValidator.validateSellerForLogin("Jack@infosys.com", "Jack23");
		Assert.assertFalse(false);
		
	}

	@Test
	public void validateSellerRegisterValidFormat() throws Exception{
		Seller seller =new Seller();
		seller.setName("Jerry Abrahm");
		seller.setEmailId("Jerry1992@infosys.com");
		seller.setPassword("Jerry@123");
		seller.setPhoneNumber("9234567890");
		seller.setAddress("2nd Main, Building No.4, Jill Square, New Orleans, US");
		SellerValidator.validateSellerForRegistration(seller);
	}
	
	@Test
	public void validateSellerRegisterInvalidEmailId() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerValidator.INVALID_EMAIL_FORMAT");
		Seller seller =new Seller();
		seller.setEmailId("Ja@ck12@infosys.com");
		seller.setPhoneNumber("9876543210");
		seller.setName("Jack");
		seller.setPassword("Jack@123");
		SellerValidator.validateSellerForRegistration(seller);
	}
	
	@Test
	public void validateSellerRegisterInvalidName1() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerValidator.INVALID_NAME");
		Seller seller =new Seller();
		seller.setEmailId("Jack12@infosys.com");
		seller.setPhoneNumber("9876543210");
		seller.setName("12Jack");
		seller.setPassword("Jack@123");
		SellerValidator.validateSellerForRegistration(seller);
	}
	
	@Test
	public void validateSellerRegisterInvalidName2() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerValidator.INVALID_NAME");
		Seller seller =new Seller();
		seller.setEmailId("Jack12@infosys.com");
		seller.setPhoneNumber("9876543210");
		seller.setName("Jack  jill");
		seller.setPassword("Jack@123");
		SellerValidator.validateSellerForRegistration(seller);
	}
	
	@Test
	public void validateSellerRegisterInvalidName3() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerValidator.INVALID_NAME");
		Seller seller =new Seller();
		seller.setEmailId("Jack12@infosys.com");
		seller.setPhoneNumber("9876543210");
		seller.setName("Jack  ");
		seller.setPassword("Jack@123");
		SellerValidator.validateSellerForRegistration(seller);
	}
	
	@Test
	public void validateSellerRegisterInvalidName4() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerValidator.INVALID_NAME");
		Seller seller =new Seller();
		seller.setEmailId("Jack12@infosys.com");
		seller.setPhoneNumber("9876543210");
		seller.setName("#Jack");
		seller.setPassword("Jack@123");
		SellerValidator.validateSellerForRegistration(seller);
	}
	
	
	@Test
	public void validateSellerRegisterInvalidName5() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerValidator.INVALID_NAME");
		Seller seller =new Seller();
		seller.setEmailId("Jack12@infosys.com");
		seller.setPhoneNumber("9876543210");
		seller.setName("   ");
		seller.setPassword("Jack@123");
		SellerValidator.validateSellerForRegistration(seller);
	}
	
	@Test
	public void validateSellerRegisterInvalidName6() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerValidator.INVALID_NAME");
		Seller seller =new Seller();
		seller.setEmailId("Jack12@infosys.com");
		seller.setPhoneNumber("9876543210");
		seller.setName("Jack1Jill");
		seller.setPassword("Jack@123");
		SellerValidator.validateSellerForRegistration(seller);
	}
	
	@Test
	public void validateSellerRegisterInvalidName7() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerValidator.INVALID_NAME");
		Seller seller =new Seller();
		seller.setEmailId("Jack12@infosys.com");
		seller.setPhoneNumber("9876543210");
		seller.setName(" Jack Jill");
		seller.setPassword("Jack@123");
		SellerValidator.validateSellerForRegistration(seller);
	}
	
	
	@Test
	public void validateSellerRegisterInvalidPhoneNumber1() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerValidator.INVALID_PHONE_NUMBER");
		Seller seller =new Seller();
		seller.setEmailId("Jack12@infosys.com");
		seller.setPhoneNumber("d987Ab321e");
		seller.setName("Jack");
		seller.setPassword("Jack@123");
		SellerValidator.validateSellerForRegistration(seller);
	}
	
	@Test
	public void validateSellerRegisterInvalidPhoneNumber2() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerValidator.INVALID_PHONE_NUMBER");
		Seller seller =new Seller();
		seller.setEmailId("Jack12@infosys.com");
		seller.setPhoneNumber("12345");
		seller.setName("Jack");
		seller.setPassword("Jack@123");
		SellerValidator.validateSellerForRegistration(seller);
	}
	
	@Test
	public void validateSellerRegisterInvalidPassword1() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerValidator.INVALID_PASSWORD");
		Seller seller =new Seller();
		seller.setEmailId("Jack12@infosys.com");
		seller.setPhoneNumber("9876543210");
		seller.setName("Jack");
		seller.setPassword("a123");
		SellerValidator.validateSellerForRegistration(seller);
	}
	
	@Test
	public void validateSellerRegisterInvalidPassword2() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerValidator.INVALID_PASSWORD");
		Seller seller =new Seller();
		seller.setEmailId("Jack12@infosys.com");
		seller.setPhoneNumber("9876543210");
		seller.setName("Jack");
		seller.setPassword("ABcd4567894321bdeklmsbx");
		SellerValidator.validateSellerForRegistration(seller);
	}
	
	@Test
	public void validateSellerRegisterInvalidPassword3() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerValidator.INVALID_PASSWORD");
		Seller seller =new Seller();
		seller.setEmailId("Jack12@infosys.com");
		seller.setPhoneNumber("9876543210");
		seller.setName("Jack");
		seller.setPassword("jackjill123");
		SellerValidator.validateSellerForRegistration(seller);
	}
	
	@Test
	public void validateSellerRegisterInvalidPassword4() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerValidator.INVALID_PASSWORD");
		Seller seller =new Seller();
		seller.setEmailId("Jack12@infosys.com");
		seller.setPhoneNumber("9876543210");
		seller.setName("Jack");
		seller.setPassword("JACKJILL123");
		SellerValidator.validateSellerForRegistration(seller);
	}
	
	@Test
	public void validateSellerRegisterInvalidPassword5() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerValidator.INVALID_PASSWORD");
		Seller seller =new Seller();
		seller.setEmailId("Jack12@infosys.com");
		seller.setPhoneNumber("9876543210");
		seller.setName("Jack");
		seller.setPassword("45@123456789");
		SellerValidator.validateSellerForRegistration(seller);
	}
	
	@Test
	public void validateSellerRegisterInvalidPassword6() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerValidator.INVALID_PASSWORD");
		Seller seller =new Seller();
		seller.setEmailId("Jack12@infosys.com");
		seller.setPhoneNumber("9876543210");
		seller.setName("Jack");
		seller.setPassword("Jack12345");
		SellerValidator.validateSellerForRegistration(seller);
	}
	

	@Test
	public void validateSellerRegisterInvalidPassword7() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerValidator.INVALID_PASSWORD");
		Seller seller =new Seller();
		seller.setEmailId("Jack12@infosys.com");
		seller.setPhoneNumber("9876543210");
		seller.setName("Jack");
		seller.setPassword("!@#$%^&*");
		SellerValidator.validateSellerForRegistration(seller);
	}
	
	@Test
	public void validateSellerRegisterInvalidPassword8() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerValidator.INVALID_PASSWORD");
		Seller seller =new Seller();
		seller.setEmailId("Jack12@infosys.com");
		seller.setPhoneNumber("9876543210");
		seller.setName("Jack");
		seller.setPassword(" asSDFADfg#$%");
		SellerValidator.validateSellerForRegistration(seller);
	}
	
	@Test
	public void validateSellerForUpdateProfileValidFormat() throws Exception{
		Seller seller =new Seller();
		seller.setName("Jack Roger");
		seller.setPhoneNumber("9876543210");
		seller.setAddress("Ist Main, Building No.3, Park Square, Salem, US");
		SellerValidator.validateSellerForUpdateProfile(seller);
	}
	
	
	@Test
	public void validateSellerForUpdateProfileInvalidFormat1() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerValidator.INVALID_PHONE_NUMBER");
		Seller seller =new Seller();
		seller.setName("Jack");
		seller.setPhoneNumber("987654321");
		seller.setAddress("Ist Main, Building No.3, Park Square, Salem, US");
		SellerValidator.validateSellerForUpdateProfile(seller);
	}
	
	
	
	
	@Test
	public void validateSellerForUpdateProfileInvalidFormat2() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerValidator.INVALID_NAME");
		Seller seller =new Seller();
		seller.setName("1Jack");
		seller.setPhoneNumber("9876543210");
		seller.setAddress("Ist Main, Building No.3, Park Square, Salem, US");
		SellerValidator.validateSellerForUpdateProfile(seller);
	}
	
	@Test
	public void validateSellerForUpdateProfileInvalidFormat3() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerValidator.INVALID_ADDRESS");
		Seller seller =new Seller();
		seller.setName("Jack");
		seller.setPhoneNumber("9876543210");
		seller.setAddress("Ist Main");
		SellerValidator.validateSellerForUpdateProfile(seller);
	}
	
	
	
	
	@Test
	public void validateSellerNameValidFormat1() throws Exception{
		Boolean result = SellerValidator.validateName("Jack Roger");
		Assert.assertTrue(result);
	}
	
	@Test
	public void validateSellerNameValidFormat2() throws Exception{
		Boolean result = SellerValidator.validateName("Jack");
		Assert.assertTrue(result);
	}
	
	@Test
	public void validateSellerNameInvalidFormat1() throws Exception{
		Boolean result = SellerValidator.validateName(" ");
		Assert.assertFalse(result);
	}
	
	@Test
	public void validateSellerNameInvalidFormat2() throws Exception{
		Boolean result = SellerValidator.validateName("1");
		Assert.assertFalse(result);
	}
	
	@Test
	public void validateSellerNameInvalidFormat3() throws Exception{
		Boolean result = SellerValidator.validateName("1 Jack ");
		Assert.assertFalse(result);
	}
	
	
	
	
	
	@Test
	public void validateSellerPhoneNumberValidFormat() throws Exception{
		Boolean result = SellerValidator.validatePhoneNumber("9876543210");
		Assert.assertTrue(result);
		
	}
	
	
	@Test
	public void validateSellerPhoneNumberInvalidFormat1() throws Exception{
		Boolean result = SellerValidator.validatePhoneNumber("987654321");
		Assert.assertFalse(result);
		
	}
	
	
	@Test
	public void validateSellerPhoneNumberInvalidFormat2() throws Exception{
		Boolean result = SellerValidator.validatePhoneNumber("A987654321");
		Assert.assertFalse(result);
		
	}
	
	
	@Test
	public void validateAddressValidFormat() throws Exception{
		Boolean result = SellerValidator.validateAddress("Hno. 16 Cross Street South lines");
		Assert.assertTrue(result);
		
	}
	
	
	@Test
	public void validateAddressInvalidFormat() throws Exception{
		Boolean result = SellerValidator.validateAddress("Hno. 16");
		Assert.assertFalse(result);
		
	}

}
