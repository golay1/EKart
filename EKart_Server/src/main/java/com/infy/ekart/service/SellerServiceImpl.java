package com.infy.ekart.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.ekart.dao.SellerDAO;
import com.infy.ekart.model.Seller;
import com.infy.ekart.utility.HashingUtility;
import com.infy.ekart.validator.SellerValidator;


@Service( value = "sellerService" )
@Transactional
public class SellerServiceImpl implements SellerService{


	@Autowired
	private SellerDAO sellerDAO;
	
	@Override
	public Seller authenticateSeller(String emailId, String password)
			throws Exception {
		
		Seller seller = null;
		emailId = emailId.toLowerCase();
		
		SellerValidator.validateSellerForLogin(emailId, password);

		String passwordFromDB = sellerDAO.getPasswordOfSeller(emailId);
		if(passwordFromDB!=null){
			String hashedPassword = HashingUtility.getHashValue(password);

			if(hashedPassword.equals(passwordFromDB)){
				seller  = sellerDAO.getSellerByEmailId(emailId);
			}
			else
				throw new Exception ("SellerService.INVALID_CREDENTIALS");
		}
		else
			throw new Exception ("SellerService.INVALID_CREDENTIALS");

		return seller;
	}
	


	@Override
	public String registerNewSeller(Seller seller) throws Exception{
		
		String registeredWithEmailId = null;
		
		SellerValidator.validateSellerForRegistration(seller);
		Boolean available = sellerDAO.checkAvailabilityOfEmailId(seller.getEmailId().toLowerCase());
		if(available){
			
			if(sellerDAO.checkAvailabilityOfPhoneNumber(seller.getPhoneNumber())){
				String emailIdToDB = seller.getEmailId().toLowerCase();
				String passwordToDB = HashingUtility.getHashValue(seller.getPassword());
				
				seller.setEmailId(emailIdToDB);
				seller.setPassword(passwordToDB);
				
				registeredWithEmailId = sellerDAO.registerNewSeller(seller);
				
			}
			else{
				throw new Exception("SellerService.PHONE_NUMBER_ALREADY_IN_USE");
			}
		}
		else{
			throw new Exception("SellerService.EMAIL_ID_ALREADY_IN_USE");
		}
		
		return registeredWithEmailId;
		
	}
	
	@Override
	public void updateProfile(Seller seller) throws Exception {
		
		Seller sellerFromDB=null;
		 
		SellerValidator.validateSellerForUpdateProfile(seller);
		sellerFromDB=sellerDAO.getSellerByPhoneNumber(seller.getPhoneNumber());
		
		if(sellerFromDB==null){
			sellerDAO.updateProfile(seller);
		}
		else{
			if(sellerFromDB.getEmailId().equals(seller.getEmailId()))
				sellerDAO.updateProfile(seller);
			else
				throw new Exception("SellerService.PHONE_NUMBER_ALREADY_IN_USE");
		}
	}
	
	@Override
	public void changePassword(Seller seller) throws  Exception {
		
		SellerValidator.validatePasswordsForSellerChangePassword(seller);
		if(seller.getPassword().equals(seller.getNewPassword()))
			throw new Exception("SellerService.OLD_PASSWORD_NEW_PASSWORD_SAME");
		String hashedPasswordFromDB = sellerDAO.getPasswordOfSeller(seller.getEmailId());
		String hashedCurrentPassword = HashingUtility.getHashValue(seller.getPassword());
		if(!hashedPasswordFromDB.equals(hashedCurrentPassword))
			throw new Exception("SellerService.INVALID_CURRENT_PASSWORD");
			
		
		String newHashedPassword = HashingUtility.getHashValue(seller.getNewPassword());
		sellerDAO.changePassword(seller.getEmailId(), newHashedPassword); 
		
	}

}
