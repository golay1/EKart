
package com.infy.ekart.validator;

import com.infy.ekart.model.Address;
import com.infy.ekart.model.Customer;

public class CustomerValidator {


	public static void validateCustomerForUpdateProfile(Customer customer) throws Exception{

		if( ! validateName(customer.getName()))
			throw new Exception("CustomerValidator.INVALID_NAME");

		if( ! validatePhoneNumber(customer.getPhoneNumber()))
			throw new Exception("CustomerValidator.INVALID_PHONE_NUMBER");

	}

	public static void validateCustomer(Customer customer) throws Exception {
			
			if(!validateEmailId(customer.getEmailId()))
				throw new Exception("CustomerValidator.INVALID_EMAIL_FORMAT");
			
			if(!validatePhoneNumber(customer.getPhoneNumber()))
				throw new Exception("CustomerValidator.INVALID_PHONE_NUMBER");
			
			if(!validateName(customer.getName()))
				throw new Exception("CustomerValidator.INVALID_NAME");
			
			if(!validatePassword(customer.getPassword()))
				throw new Exception("CustomerValidator.INVALID_PASSWORD_FORMAT");
				
		}
	
		public static void validateAddress(Address address) throws Exception{
				
				if ( ! isValidAddressLine1(address.getAddressLine1()) )
					throw new Exception("CustomerValidator.INVALID_ADDRESS_LINE_1");
				
				if ( ! validateCity(address.getCity()) )
					throw new Exception("CustomerValidator.INVALID_CITY");
				
				if ( ! validateState(address.getState() ) )
					throw new Exception("CustomerValidator.INVALID_STATE");
				
				if (! validateContactNumber(address.getContactNumber()))
					throw new Exception("CustomerValidator.INVALID_CONTACT_NUMBER");
				
				if (! validatePIN(address.getPin()))
					throw new Exception("CustomerValidator.INVALID_PIN");
				
		
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
	
	public static Boolean validateCity(String city){
		Boolean flag = false;
		if( ! city.matches("[ ]*"))
			flag=true;
		return flag;
		
	}
	
	public static Boolean validateState(String state){
		Boolean flag = false;
		if(! state.matches("[ ]*"))
			flag=true;
		return flag;
		
	}
	
	public static Boolean isValidAddressLine1(String addressLine1){
		Boolean flag = false;
		if(! addressLine1.matches("[ ]*"))
			flag=true;
		return flag;
		
	}
	

	public static Boolean validateContactNumber(String phoneNumber){

		Boolean flag = false;
		if(phoneNumber.matches("[0-9]+") && phoneNumber.length()==10)
			flag=true;
		return flag;

	}

	public static Boolean validatePIN(String pin){

		Boolean flag = false;
		if(pin.matches("[A-Za-z0-9]{6,10}"))
			flag=true;
		return flag;

	} 
}
