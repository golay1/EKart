package com.infy.ekart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.ekart.dao.CustomerDAO;
import com.infy.ekart.model.Address;
import com.infy.ekart.model.Customer;
import com.infy.ekart.validator.CustomerValidator;

@Service( value = "customerService" )
@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerDAO customerDAO;
	
	@Override
	public Customer authenticateCustomer(String emailId, String password)
			throws Exception{

		Customer customer = null;
		String customerEmailIdFromDAO = customerDAO.authenticateCustomer(emailId.toLowerCase(), password);
		if(customerEmailIdFromDAO!=null){
			
				customer  = customerDAO.getCustomerByEmailId(customerEmailIdFromDAO);
		}
		else
			throw new Exception ("CustomerService.INVALID_CREDENTIALS");
		
		return customer;
		
	}
	
	@Override
	public String registerNewCustomer(Customer customer)
			throws Exception {
		
		String registeredWithEmailId = null;

		CustomerValidator.validateCustomer(customer);
		Boolean emailAvailable = customerDAO.checkAvailabilityOfEmailId(customer.getEmailId().toLowerCase());
		Boolean phoneNumberAvailable = customerDAO.checkAvailabilityOfPhoneNumber(customer.getPhoneNumber());
		if(emailAvailable){
			if(phoneNumberAvailable){
				String emailIdToDB = customer.getEmailId().toLowerCase();

				customer.setEmailId(emailIdToDB);

				registeredWithEmailId = customerDAO.registerNewCustomer(customer);

			}
			else{
				throw new Exception("CustomerService.PHONE_NUMBER_ALREADY_IN_USE");
			}
		}
		else{
			throw new Exception("CustomerService.EMAIL_ID_ALREADY_IN_USE");
		}


		return registeredWithEmailId;
	}
	
	@Override
	public void updateProfile(Customer customer) throws  Exception {
		
		Customer newCustomer=null;
		CustomerValidator.validateCustomerForUpdateProfile(customer);

		newCustomer=customerDAO.getCustomerByPhoneNumber(customer.getPhoneNumber());
		if(newCustomer==null){
			customerDAO.updateProfile(customer);
		}
		else{
			if(newCustomer.getEmailId().equals(customer.getEmailId()))
				customerDAO.updateProfile(customer);
			else
				throw new Exception("CustomerService.PHONE_NUMBER_ALREADY_IN_USE");
		}
	}

	@Override
	public void changePassword(String customerEmailId, String currentPassword, String newPassword)
			throws Exception {

		Boolean validPassword = CustomerValidator.validatePassword(newPassword);
		if (!validPassword)
			throw new Exception("CustomerService.INVALID_NEW_PASSWORD");
		
		String passwordFromDB = customerDAO.getPasswordOfCustomer(customerEmailId);
		
		if(!passwordFromDB.equals(currentPassword))
			throw new Exception("CustomerService.INVALID_CURRENT_PASSWORD");
		
		if(currentPassword.equals(newPassword))
			throw new Exception("CustomerService.OLD_PASSWORD_NEW_PASSWORD_SAME");
		
		customerDAO.changePassword(customerEmailId, newPassword);

	}
	
	@Override
	public Integer addShippingAddress(String customerEmailId, Address address)
			throws Exception {
		
		CustomerValidator.validateAddress(address);
		Integer newAddressID = customerDAO.addShippingAddress(customerEmailId, address);
		
		return newAddressID; 
	}
	
	@Override
	public void updateShippingAddress(Address address) throws Exception {
		
		CustomerValidator.validateAddress(address);
		customerDAO.updateShippingAddress(address);
		
	}
	

	@Override
	public void deleteShippingAddress(String customerEmailId, Integer addressId)
			throws Exception {
		
		customerDAO.deleteShippingAddress(customerEmailId, addressId );
	}

	 @Override
	   public Address getShippingAddress(Integer addressId) throws Exception {
	       
	       return customerDAO.getShippingAddress(addressId);
	       
	   }
}
