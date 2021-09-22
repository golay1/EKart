package com.infy.ekart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.ekart.dao.SellerRecommendedProductDAO;
import com.infy.ekart.model.Product;

@Service( value = "sellerRecommendProductService" )
@Transactional
public class SellerRecommendProductServiceImpl implements SellerRecommendProductService {
	
	@Autowired
	private SellerRecommendedProductDAO SellerRecommendedProductDAO;
	
	@Override
	public List<Product> getRecommendedProductList( String emailId) throws Exception {
		
		return SellerRecommendedProductDAO.getRecommendedProductList( emailId);
	}

	@Override
	public int deleteRecommendedProduct(String emailId, int productId) throws Exception {
		
		int updated = SellerRecommendedProductDAO.deleteRecommendedProduct(emailId, productId);
		if (updated == 0) {
			throw new Exception("RecommendedProductService.NO_VALID_ROWS_TO_DELETE");
		}
		return updated;
	}

	@Override
	public void addRecommendedProduct(int productId, String emailId) throws Exception{
		SellerRecommendedProductDAO.addRecommendedProduct(emailId, productId);
		
	}
}
