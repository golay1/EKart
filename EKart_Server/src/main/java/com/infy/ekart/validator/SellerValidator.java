package com.infy.ekart.validator;

import com.infy.ekart.model.Seller;

public class SellerValidator {
	
	public static void validateSellerForUpdateProfile(Seller seller) throws Exception{
		
		if( ! validateName(seller.getName()))
			throw new Exception("SellerValidator.INVALID_NAME");
		
		if( ! validatePhoneNumber(seller.getPhoneNumber()))
			throw new Exception("SellerValidator.INVALID_PHONE_NUMBER");
		
		if( ! validateAddress(seller.getAddress()))
			throw new Exception("SellerValidator.INVALID_ADDRESS");
	}
	

	public static void validateSellerForLogin(String emailId, String password) throws Exception{
		if( !validateEmailId(emailId) )
			throw new Exception("SellerValidator.INVALID_EMAIL_FORMAT_FOR_LOGIN");
		
		if( !validatePassword(password) )
			throw new Exception("SellerValidator.INVALID_PASSWORD_FORMAT_FOR_LOGIN");
	}

	public static void validateSellerForRegistration(Seller seller) throws Exception {
		if(!validateEmailId(seller.getEmailId()))
			throw new Exception("SellerValidator.INVALID_EMAIL_FORMAT");
		
		if(!validatePhoneNumber(seller.getPhoneNumber()))
			throw new Exception("SellerValidator.INVALID_PHONE_NUMBER");
		
		if(!validateName(seller.getName()))
			throw new Exception("SellerValidator.INVALID_NAME");
		
		if(!validatePassword(seller.getPassword()))
			throw new Exception("SellerValidator.INVALID_PASSWORD_FORMAT");
		
		if( ! validateAddress(seller.getAddress()))
			throw new Exception("SellerValidator.INVALID_ADDRESS");
	}
	
	public static void validatePasswordsForSellerChangePassword(Seller seller) throws Exception {
		if(! validatePassword(seller.getPassword()))
			throw new Exception("SellerValidator.INVALID_CURRENT_PASSWORD_FOR_CHANGE_PASSWORD");
		
		if(! validatePassword(seller.getNewPassword()))
			throw new Exception("SellerValidator.INVALID_NEW_PASSWORD");
		
		if(!seller.getNewPassword().equals(seller.getConfirmNewPassword()))
			throw new Exception("SellerValidator.NEW_PASSWORD_AND_CONFIRM_NEW_PASSWORD_MISMATCH");
			
		
	}
	public static Boolean validateName(String name){
		Boolean flag = false;
		if(!name.matches("[ ]*") && name.matches("([A-Za-z])+(\\s[A-Za-z]+)*"))
			flag=true;
		return flag;
	}
	
	public static Boolean validatePhoneNumber(String phoneNumber){
		Boolean flag = false;
		if(phoneNumber.matches("[0-9]+") && phoneNumber.length()==10)
			flag=true;
		return flag;
	}
	
	public static Boolean validateAddress(String address){
		Boolean flag = false;
		if(address.length()>=10)
			flag=true;
		return flag;
	}
	
	public static Boolean validateEmailId(String emailId){
		Boolean flag = false;
		if(emailId.matches("[a-zA-Z0-9._]+@[a-zA-Z]{2,}\\.[a-zA-Z][a-zA-Z.]+"))
			flag = true;
		return flag;
	}
	
	
	public static Boolean validatePassword(String password){
		Boolean flag = false;
		if (password.length()>=6 && password.length()<=20)
			if(password.matches(".*[A-Z]+.*"))
				if(password.matches(".*[a-z]+.*"))
					if(password.matches(".*[0-9]+.*"))
						if(password.matches(".*[^a-zA-Z-0-9].*"))
							flag = true;
		return flag;
	}


}
