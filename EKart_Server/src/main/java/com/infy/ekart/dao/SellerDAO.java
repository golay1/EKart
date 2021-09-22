package com.infy.ekart.dao;

import com.infy.ekart.model.Seller;

public interface SellerDAO {

	public String getPasswordOfSeller(String emailId);

	public Seller getSellerByEmailId(String emailId) throws Exception;
	
	public Boolean checkAvailabilityOfEmailId(String emailId);
	
	public Boolean checkAvailabilityOfPhoneNumber(String phoneNumber);
	
	public String registerNewSeller(Seller seller);
	
	public Seller getSellerByPhoneNumber(String phoneNumber);
	
	public void updateProfile(Seller seller);
	
	public void changePassword(String sellerEmailId, String newHashedPassword);

}
