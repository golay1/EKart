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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.infy.ekart.model.Product;
import com.infy.ekart.service.SellerProductService;


@CrossOrigin
@RestController
@RequestMapping("SellerProductAPI")
public class SellerProductAPI {
	
	@Autowired
	private SellerProductService sellerProductService;
	
	@Autowired
	private Environment environment;
	
	static Logger logger = LogManager.getLogger(SellerAPI.class.getName());
	
	@PostMapping(value = "addProductToSellerCatalog")
	public ResponseEntity<?> addNewProductToSellerCatalog(@RequestBody Product product) throws Exception {
		try
		{
			logger.info("ADDING PRODUCT TO SELLER CATALOG, PRODUCT NAME: "+product.getName()+"\tSELLER NAME = "+product.getSellerEmailId());
			
			Integer newProductId = sellerProductService.addNewProduct(product);
			
			logger.info("PRODUCT ADDED SUCCESSFULLY TO SELLER CATALOG, PRODUCT NAME: "+product.getName()+"\tSELLER NAME = "+product.getSellerEmailId());
			
			String successMessage = environment.getProperty("SellerProductAPI.PRODUCT_ADDED_SUCCESSFULLY")+newProductId;
			
			product.setSuccessMessage(successMessage);
			
			product.setProductId(newProductId);
			
			return new ResponseEntity<Product>(product, HttpStatus.OK);
			
		}
		catch (Exception e){
			if(e.getMessage().contains("Validator")){
				throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, environment.getProperty(e.getMessage()));
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}

	@PostMapping(value = "modifyProductDetails")
	public ResponseEntity<?> modifyProductDetails(@RequestBody Product product) throws Exception {
		try
		{
			logger.info("UPDATING PRODUCT DETAILS, PRODUCT ID: "+product.getProductId());
			
			Product productReturned = sellerProductService.modifyProductDetails(product);
			
			logger.info("PRODUCT DETAILS UPDATED SUCCESSFULLY, PRODUCT ID: "+product.getProductId());
			
			String successMessage = environment.getProperty("SellerProductAPI.PRODUCT_MODIFIED_SUCCESSFULLY")+productReturned.getProductId();
			
			productReturned.setSuccessMessage(successMessage);
			
			return new ResponseEntity<Product>(productReturned, HttpStatus.OK);
		}
		catch (Exception e){
			if(e.getMessage().contains("Validator")){
				throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, environment.getProperty(e.getMessage()));
			}
			throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, environment.getProperty(e.getMessage()));
		}
	}
	
	@PostMapping(value = "removeProduct")
	public ResponseEntity<?> removeProduct(@RequestBody Product product) throws Exception {
		try
		{
			logger.info("REMOVING PRODUCT DETAILS, PRODUCT ID: "+product.getProductId());
			
			Integer deleteProduct = sellerProductService.removeProduct(product);
			
			logger.info("PRODUCT DETAILS REMOVED SUCCESSFULLY, PRODUCT ID: "+product.getProductId());
			
			String successMessage = environment.getProperty("SellerProductAPI.PRODUCT_DELETED_SUCCESSFULLY")+deleteProduct;
			product.setProductId(deleteProduct);
			product.setSuccessMessage(successMessage);
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		}

        catch (Exception e) {
		
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}

	@GetMapping(value = "getProductCategories")
	public ResponseEntity<List<String>> getProductCategories() throws Exception {
		try
		{
			List<String> productCategories = sellerProductService.getProductCategoryList();
			return new ResponseEntity<List<String>>(productCategories, HttpStatus.OK);
		}

        catch (Exception e) {
		
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
	
	
}
