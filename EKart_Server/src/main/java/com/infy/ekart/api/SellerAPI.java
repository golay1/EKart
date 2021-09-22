package com.infy.ekart.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.infy.ekart.model.Seller;
import com.infy.ekart.service.SellerService;


@CrossOrigin
@RestController
@RequestMapping("SellerAPI")
public class SellerAPI {
	
	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private SellerService sellerLoginService;
	
	@Autowired
	private Environment environment;
	
	static Logger logger = LogManager.getLogger(SellerAPI.class.getName());
	
	@PostMapping(value = "registerSeller")
	public ResponseEntity<String> registerSeller(@RequestBody Seller seller) throws Exception {
		try
		{
			logger.info("SELLER TRYING TO REGISTER. SELLER EMAIL ID: "+seller.getEmailId());
			
			String registeredWithEmailID = sellerService.registerNewSeller(seller);
			
			logger.info("SELLER REGISTRATION SUCCESSFUL. SELLER EMAIL ID: "+seller.getEmailId());
			
			registeredWithEmailID = environment.getProperty("SellerAPI.SELLER_REGISTRATION_SUCCESS")+registeredWithEmailID;
			
			return new ResponseEntity<String>(registeredWithEmailID, HttpStatus.OK);
			
		}
		catch (Exception e){
			if(e.getMessage().contains("Validator")){
				throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, environment.getProperty(e.getMessage()));
			}
			throw new ResponseStatusException(HttpStatus.CONFLICT, environment.getProperty(e.getMessage()));
		}
	}
	
	@PostMapping(value = "sellerLogin")
	public ResponseEntity<Seller> authenticateSeller(@RequestBody Seller seller) throws Exception {
		try
		{
			logger.info("SELLER TRYING TO LOGIN. SELLER EMAIL ID: "+seller.getEmailId());
			
			Seller sellerFromDB =  sellerLoginService.authenticateSeller(seller.getEmailId(), seller.getPassword());
			
			logger.info("SELLER LOGIN SUCCESSFUL. SELLER EMAIL ID: "+seller.getEmailId());
			
			return new ResponseEntity<Seller>(sellerFromDB, HttpStatus.OK);
		}
		catch (Exception e) {

			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, environment.getProperty(e.getMessage()));
		}
	}	

	@PostMapping(value = "updateProfile")
	public ResponseEntity<String> updateSellerProfile(@RequestBody Seller seller) throws Exception {
		try
		{ 
			logger.info("SELLER TRYING TO UPDATE PROFILE. SELLER EMAIL ID: "+seller.getEmailId());
			
			sellerService.updateProfile(seller);
			
			logger.info("SELLER PROFILE UPDATED SUCCESSFULLY. SELLER EMAIL ID: "+seller.getEmailId());
			
			String modificationSuccessMsg = environment.getProperty("SellerAPI.SELLER_DETAILS_UPDATION_SUCCESS");
			
			return new ResponseEntity<String>(modificationSuccessMsg, HttpStatus.OK);
			
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, environment.getProperty(e.getMessage()));
		}

	}
	
	@PostMapping(value = "changePassword")
	public ResponseEntity<String> changePassword(@RequestBody Seller seller) throws Exception {
		try
		{
			logger.info("SELLER TRYING TO CHANGE PASSWORD. SELLER EMAIL ID: "+seller.getEmailId());
			
			sellerService.changePassword(seller);
			
			logger.info("SELLER CHANGE PASSWORD SUCCESSFUL. SELLER EMAIL ID: "+seller.getEmailId());
			
			String modificationSuccessMsg = environment.getProperty("SellerAPI.SELLER_PASSWORD_CHANGE_SUCCESS");

			return new ResponseEntity<String>(modificationSuccessMsg, HttpStatus.OK);
			
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, environment.getProperty(e.getMessage()));
		}

	}
	
}
