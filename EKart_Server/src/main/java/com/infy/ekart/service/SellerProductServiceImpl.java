package com.infy.ekart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.ekart.dao.SellerProductDAO;
import com.infy.ekart.model.Product;
import com.infy.ekart.validator.SellerProductValidator;


@Service( value = "sellerProductService" )
@Transactional
public class SellerProductServiceImpl implements
		SellerProductService {
	
	@Autowired
	private SellerProductDAO sellerProductDAO;
	
	@Override
	public Integer addNewProduct(Product product) throws Exception {
		
		SellerProductValidator.validateProductForAddNewProduct(product);

		Integer productId = sellerProductDAO.addNewProduct(product);
		
		return productId;
	}
	
	@Override
	public Product modifyProductDetails(Product productForModification)
			throws Exception {
		
		
		SellerProductValidator.validateProductForModifyProductDetails(productForModification);
		
		Product product = sellerProductDAO.modifyProductDetails(productForModification);
		
		return product;
	}


	@Override
	public Integer removeProduct(Product product) {

		return sellerProductDAO.removeProduct(product.getSellerEmailId(),product.getProductId());
		
	}
	
	@Override
	public List<String> getProductCategoryList() throws Exception {
		
		return sellerProductDAO.getProductCategoryList();
	}

}
