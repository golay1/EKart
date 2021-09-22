package com.infy.ekart.validator;

import com.infy.ekart.model.Product;

public class SellerProductValidator {

	public static void validateProductForAddNewProduct(Product product) throws Exception{
		if(! isValidProductName(product.getName()))
			throw new Exception("SellerProductValidator.INVALID_NAME");
		
		if(! isValidProductDescription(product.getDescription()))
			throw new Exception("SellerProductValidator.INVALID_DESCRIPTION");
		
		if(!isValidDiscount(product.getDiscount()))
			throw new Exception("SellerProductValidator.INVALID_DISCOUNT");
		
		if(!isValidQuantity(product.getQuantity()))
			throw new Exception("SellerProductValidator.INVALID_QUANTITY");
		
		if(!isValidPrice(product.getPrice() ) )
			throw new Exception("SellerProductValidator.INVALID_PRICE");
		
		if(!isValidBrand(product.getBrand()))
			throw new Exception("SellerProductValidator.INVALID_BRAND");
	}
	
	public static void validateProductForModifyProductDetails(Product product) throws Exception{
		if(! isValidProductName(product.getName()))
			throw new Exception("SellerProductValidator.INVALID_NAME");
		
		if(! isValidProductDescription(product.getDescription()))
			throw new Exception("SellerProductValidator.INVALID_DESCRIPTION");
		
		if(!isValidDiscount(product.getDiscount()))
			throw new Exception("SellerProductValidator.INVALID_DISCOUNT");
		
		if(!isValidQuantity(product.getQuantity()))
			throw new Exception("SellerProductValidator.INVALID_QUANTITY");
		
		if(!isValidPrice(product.getPrice() ) )
			throw new Exception("SellerProductValidator.INVALID_PRICE");
		
	}
	
	public static Boolean isValidProductName(String productName){
		Boolean flag = false;
		if(!productName.matches("[ ]*") && productName.matches("([A-Za-z0-9-.])+(\\s[A-Za-z0-9-.]+)*"))
			flag=true;
		return flag;
		
	}
	
	public static Boolean isValidProductDescription(String productDescription){
		Boolean flag = false;
		if(! productDescription.matches("[ ]*") && productDescription.length()>=10)
			flag=true;
		return flag;
		
	}
	
	public static Boolean isValidDiscount(Double discount){
		Boolean flag = false;
		if(discount>=0.0 && discount<100.0)
			flag = true;
		return flag;
		
	}
	
	
	public static Boolean isValidQuantity(Integer quantity){
		Boolean flag = false;
		if(quantity>0)
			flag = true;
		return flag;
		
	}
	
	public static Boolean isValidBrand(String brand){
		Boolean flag = false;
		if(!brand.matches("[ ]*") && brand.length()>=3)
			flag = true;
		return flag;
		
	}
	
	public static Boolean isValidPrice(Double price){
		Boolean flag = false;
		if(price>0.0)
			flag = true;
		return flag;
		
	}
}
