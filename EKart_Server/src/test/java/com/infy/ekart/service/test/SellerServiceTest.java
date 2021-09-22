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

import com.infy.ekart.dao.SellerDAO;
import com.infy.ekart.model.Seller;
import com.infy.ekart.service.SellerService;
import com.infy.ekart.service.SellerServiceImpl;
import com.infy.ekart.utility.HashingUtility;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerServiceTest {
	@Mock
	private SellerDAO sellerDAO;
	@InjectMocks
	private SellerService sellerService=new SellerServiceImpl();
	@Rule
	public ExpectedException expectedException=ExpectedException.none();
	
	@Test
	public void testAuthenticateSeller() throws Exception{
		String email="jack@infosys.com";
		String password="Jack@123";
		String hashedPassword = HashingUtility.getHashValue(password);

		Mockito.when(sellerDAO.getPasswordOfSeller(email)).thenReturn(hashedPassword);
		Mockito.when(sellerDAO.getSellerByEmailId(email)).thenReturn(new Seller());
		Assert.assertNotNull(sellerService.authenticateSeller(email, password));
	}
	@Test
	public void testAuthenticateSellerInvalid() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerService.INVALID_CREDENTIALS");
		String email="Jack@infosys.com";
		String password="Jack@123";
		
		Mockito.when(sellerDAO.getPasswordOfSeller(email)).thenReturn(password);
		Mockito.when(sellerDAO.getSellerByEmailId(email)).thenReturn(new Seller());
		Assert.assertNotNull(sellerService.authenticateSeller(email, password));
	}
	@Test
	public void testAuthenticateSellerInvalidEmail() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerValidator.INVALID_EMAIL_FORMAT");
		String email="Jac77897";
		String password="Jack@123";
		
		Mockito.when(sellerDAO.getPasswordOfSeller(email)).thenReturn(password);
		Mockito.when(sellerDAO.getSellerByEmailId(email)).thenReturn(new Seller());
		Assert.assertNotNull(sellerService.authenticateSeller(email, password));
	}
	@Test
	public void testAuthenticateSellerInvalidPassword() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerValidator.INVALID_PASSWORD_FORMAT");
		String email="Jack@gmail.com";
		String password="jack@3";
		
		Assert.assertNotNull(sellerService.authenticateSeller(email, password));
	}
	@Test
	public void testAuthenticateSellerInvalidFormat() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerService.INVALID_CREDENTIALS");
		String email="Jack@infosys.com";
		String password="Jack@123";
		
		Mockito.when(sellerDAO.getPasswordOfSeller(email)).thenReturn(null);
		Mockito.when(sellerDAO.getSellerByEmailId(email)).thenReturn(new Seller());
		Assert.assertNotNull(sellerService.authenticateSeller(email, password));
	}
	
	@Test
	public void testChangePassword() throws Exception{
		Seller seller =new Seller();
		String newPassword="Charles@423";
		String currentPassword="Charles@123";
		seller.setEmailId("charles@infosys.com");
		seller.setPassword(currentPassword);
		seller.setNewPassword(newPassword);
		seller.setConfirmNewPassword(newPassword);
		String hashCurrentPassword = HashingUtility.getHashValue(currentPassword);
		String hashNewPassword = HashingUtility.getHashValue(newPassword);

		Mockito.when(sellerDAO.getPasswordOfSeller(Mockito.anyString())).thenReturn(hashCurrentPassword);
		Mockito.doNothing().when(sellerDAO).changePassword("", hashNewPassword);
		sellerService.changePassword(seller);

	}
	@Test
	public void testChangePasswordInvalidNewPassword() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerValidator.INVALID_NEW_PASSWORD");
		
		Seller seller =new Seller();
		String newPassword="jack123123";
		String currentPassword="Jack@123";
		seller.setEmailId("jack@infosys.com");
		seller.setPassword(currentPassword);
		seller.setNewPassword(newPassword);
		sellerService.changePassword(seller);
	

	}
	@Test
	public void testChangePasswordInvalidCurrentPassword() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerService.INVALID_CURRENT_PASSWORD");
		Seller seller =new Seller();
		String newPassword="Jack@423";
		String currentPassword="Jack@1123";
		seller.setEmailId("jack@infosys.com");
		seller.setPassword(currentPassword);
		seller.setNewPassword(newPassword);
		seller.setConfirmNewPassword(newPassword);
		String hashCurrentPassword = HashingUtility.getHashValue(currentPassword+" g");
		String hashNewPassword = HashingUtility.getHashValue(newPassword);
		Mockito.when(sellerDAO.getPasswordOfSeller(Mockito.anyString())).thenReturn(hashCurrentPassword);
		Mockito.doNothing().when(sellerDAO).changePassword("", hashNewPassword);
		sellerService.changePassword(seller);

	}
	
	@Test
	public void testChangePasswordInvalidPassword() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerService.OLD_PASSWORD_NEW_PASSWORD_SAME");
		Seller seller =new Seller();
		String newPassword="Jack@123";
		String currentPassword="Jack@123";
		seller.setEmailId("jack@infosys.com");
		seller.setPassword(currentPassword);
		seller.setNewPassword(newPassword);
		seller.setConfirmNewPassword(newPassword);
		String hashCurrentPassword = HashingUtility.getHashValue(currentPassword);
		String hashNewPassword = HashingUtility.getHashValue(newPassword);
		
		Mockito.when(sellerDAO.getPasswordOfSeller(Mockito.anyString())).thenReturn(hashCurrentPassword);
		Mockito.doNothing().when(sellerDAO).changePassword("", hashNewPassword);
		sellerService.changePassword(seller);

	}
	
	@Test
	public void testRegisterNewSeller() throws Exception{
		Seller seller =new Seller();
		seller.setName("Jerry Abrahm");
		seller.setEmailId("Jerry1992@infosys.com");
		seller.setPassword("Jerry@123");
		seller.setPhoneNumber("9234567890");
		seller.setAddress("2nd Main, Building No.4, Jill Square, New Orleans, US");
		Mockito.when(sellerDAO.checkAvailabilityOfEmailId(Mockito.anyString())).thenReturn(true);
		Mockito.when(sellerDAO.checkAvailabilityOfPhoneNumber(Mockito.anyString())).thenReturn(true);
		Mockito.when(sellerDAO.registerNewSeller(seller)).thenReturn("1");
		Assert.assertNotNull(sellerService.registerNewSeller(seller));

	}
	
	@Test
	public void registerNewSellerExistingEmailID() throws Exception {
		
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerService.EMAIL_ID_ALREADY_IN_USE");
		
		Seller seller = new Seller();
		seller.setEmailId("Jerry1992@infosys.com");
		seller.setAddress("2nd main, 8th cross, Park square, NYC-332290");
		seller.setName("Jerry Abrahm");
		seller.setPhoneNumber("9234567890");
		seller.setPassword("Jerry@123");Mockito.when(sellerDAO.checkAvailabilityOfEmailId(Mockito.anyString())).thenReturn(false);
		Mockito.when(sellerDAO.checkAvailabilityOfPhoneNumber(Mockito.anyString())).thenReturn(true);
		
		sellerService.registerNewSeller(seller);
	}
	@Test
	public void registerNewSellerExistingPhoneNo() throws Exception {
		
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerService.PHONE_NUMBER_ALREADY_IN_USE");
		
		Seller seller = new Seller();
		seller.setEmailId("Jerry1992@infosys.com");
		seller.setAddress("2nd main, 8th cross, Park square, NYC-332290");
		seller.setName("Jerry Abrahm");
		seller.setPhoneNumber("9234567890");
		seller.setPassword("Jerry@123");
		Mockito.when(sellerDAO.checkAvailabilityOfEmailId(Mockito.anyString())).thenReturn(true);
		Mockito.when(sellerDAO.checkAvailabilityOfPhoneNumber(Mockito.anyString())).thenReturn(false);
		
		Assert.assertNotNull(sellerService.registerNewSeller(seller));
	}
	
	@Test
	public void testRegisterNewSellerInValidEmail() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerValidator.INVALID_EMAIL_FORMAT");
		Seller seller =new Seller();
		seller.setEmailId("Ja@ck12@infosys.com");
		seller.setPhoneNumber("9876543210");
		seller.setName("Jack");
		seller.setPassword("Jack@123");
		Assert.assertNotNull(sellerService.registerNewSeller(seller));

	}

	@Test
	public void testRegisterNewSellerInValidName() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerValidator.INVALID_NAME");
		Seller seller =new Seller();
		seller.setEmailId("Jack12@infosys.com");
		seller.setPhoneNumber("9876543210");
		seller.setName("12Jack");
		seller.setPassword("Jack@123");
		Assert.assertNotNull(sellerService.registerNewSeller(seller));

	}
	@Test
	public void testRegisterNewSellerInValidPassword() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerValidator.INVALID_PASSWORD");
		Seller seller =new Seller();
		seller.setEmailId("Jack12@infosys.com");
		seller.setPhoneNumber("9876543210");
		seller.setName("Jack");
		seller.setPassword("a123");
		Assert.assertNotNull(sellerService.registerNewSeller(seller));

	}
	@Test
	public void testRegisterNewSellerInValidPhoneNumber() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerValidator.INVALID_PHONE_NUMBER");
		Seller seller =new Seller();
		seller.setEmailId("Jack12@infosys.com");
		seller.setPhoneNumber("d987Ab321e");
		seller.setName("Jack");
		seller.setPassword("Jack@123");
		Assert.assertNotNull(sellerService.registerNewSeller(seller));

	}
	@Test
	public void testUpdateProfile() throws Exception{
		Seller seller =new Seller();
		seller.setName("Jack Roger");
		seller.setPhoneNumber("9876543210");
		seller.setAddress("Ist Main, Building No.3, Park Square, Salem, US");
		seller.setEmailId("jack@g.com");
		Seller newSeller =new Seller();
		newSeller.setEmailId("jack@g.com");
		Mockito.when(sellerDAO.getSellerByPhoneNumber(seller.getPhoneNumber())).thenReturn(newSeller);
		Mockito.doNothing().when(sellerDAO).updateProfile(seller);
		sellerService.updateProfile(seller);
		
	}
	@Test
	public void testUpdateProfilevalid() throws Exception{
		Seller seller =new Seller();
		seller.setName("Jack Roger");
		seller.setPhoneNumber("9876543210");
		seller.setAddress("Ist Main, Building No.3, Park Square, Salem, US");
		Mockito.when(sellerDAO.getSellerByPhoneNumber(seller.getPhoneNumber())).thenReturn(null);
		Mockito.doNothing().when(sellerDAO).updateProfile(seller);
		sellerService.updateProfile(seller);
		
		
	}
	@Test
	public void testUpdateProfileInvalid() throws Exception{
		Seller seller =new Seller();
		seller.setName("Jack Roger");
		seller.setPhoneNumber("9876543210");
		seller.setAddress("Ist Main, Building No.3, Park Square, Salem, US");
		seller.setEmailId("jack@gmail.com");
		Seller newSeller =new Seller();
		newSeller.setEmailId("jack@g.com");
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerService.PHONE_NUMBER_ALREADY_IN_USE");
		Mockito.when(sellerDAO.getSellerByPhoneNumber(seller.getPhoneNumber())).thenReturn(newSeller);
		Mockito.doNothing().when(sellerDAO).updateProfile(seller);
		sellerService.updateProfile(seller);
		
		
	}
	@Test
	public void testUpdateProfileInvalidName() throws Exception{
		Seller seller =new Seller();
		seller.setName("Jack ");
		seller.setPhoneNumber("9876543210");
		seller.setAddress("Ist Main, Building No.3, Park Square, Salem, US");
		seller.setEmailId("jack@gmail.com");
		Seller newSeller =new Seller();
		newSeller.setEmailId("jack@g.com");
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerValidator.INVALID_NAME");
		Mockito.when(sellerDAO.getSellerByPhoneNumber(seller.getPhoneNumber())).thenReturn(newSeller);
		Mockito.doNothing().when(sellerDAO).updateProfile(seller);
		sellerService.updateProfile(seller);
		
		
	}
	
	@Test
	public void testUpdateProfileInvalidPhoneNumber() throws Exception{
		Seller seller =new Seller();
		seller.setName("Jack");
		seller.setPhoneNumber("9876f543210");
		seller.setAddress("Ist Main, Building No.3, Park Square, Salem, US");
		seller.setEmailId("jack@gmail.com");
		Seller newSeller =new Seller();
		newSeller.setEmailId("jack@g.com");
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerValidator.INVALID_PHONE_NUMBER");
		Mockito.when(sellerDAO.getSellerByPhoneNumber(seller.getPhoneNumber())).thenReturn(newSeller);
		Mockito.doNothing().when(sellerDAO).updateProfile(seller);
		sellerService.updateProfile(seller);
		
		
	}
	@Test
	public void testUpdateProfileInvalidAddress() throws Exception{
		Seller seller =new Seller();
		seller.setName("Jack");
		seller.setPhoneNumber("9876543210");
		seller.setAddress("  ");
		seller.setEmailId("jack@gmail.com");
		Seller newSeller =new Seller();
		newSeller.setEmailId("jack@g.com");
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SellerValidator.INVALID_ADDRESS");
		Mockito.when(sellerDAO.getSellerByPhoneNumber(seller.getPhoneNumber())).thenReturn(newSeller);
		Mockito.doNothing().when(sellerDAO).updateProfile(seller);
		sellerService.updateProfile(seller);
		
		
	}
}
