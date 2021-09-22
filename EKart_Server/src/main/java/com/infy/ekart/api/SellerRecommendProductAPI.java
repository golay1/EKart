package com.infy.ekart.api;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.infy.ekart.model.Product;
import com.infy.ekart.service.SellerRecommendProductService;
@EnableWebMvc
@CrossOrigin
@RestController
@RequestMapping("SellerRecommendProductAPI")
public class SellerRecommendProductAPI {
	
	@Autowired
	private SellerRecommendProductService SellerRecommendedProductService;
	
	@Autowired
	private Environment environment;
	
	static Logger logger = LogManager.getLogger(SellerAPI.class.getName());
	
	@GetMapping(value = "getRecommendedProduct/{emailId:.+}")
	public ResponseEntity<List<Product>> getRecommendedProduct(@PathVariable("emailId") String emailId) throws Exception {	
		try
		{
			List<Product> recommendedProducts = SellerRecommendedProductService.getRecommendedProductList(emailId);
		return new ResponseEntity<List<Product>>(recommendedProducts, HttpStatus.OK);
		}
		catch (Exception e) {
		
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
	
	@PostMapping(value = "deleteRecommendedProduct/")
	public ResponseEntity<Integer> deleteRecommendedProduct(@RequestParam String emailId, @RequestParam int productId) throws Exception{
		try
		{
			int updated = SellerRecommendedProductService.deleteRecommendedProduct(emailId,productId);
			logger.info(updated + "ROWS SUCCESFULLY UPDATED!");
			return new ResponseEntity<Integer>(updated, HttpStatus.OK);
		}
		catch (Exception e) {
		
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
	@PutMapping(value = "addRecommendedProduct/{productId}/{emailId:.+}")
	public void addRecommendedProduct(@PathVariable("productId") int productId,@PathVariable("emailId") String emailId) throws Exception{
		try
		{
			SellerRecommendedProductService.addRecommendedProduct(productId,emailId);
		}
		catch (Exception e) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
}
	
