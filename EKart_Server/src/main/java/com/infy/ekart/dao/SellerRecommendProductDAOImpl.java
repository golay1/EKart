package com.infy.ekart.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.infy.ekart.entity.ProductEntity;
import com.infy.ekart.entity.RecommendedProductEntity;
import com.infy.ekart.entity.SellerEntity;
import com.infy.ekart.model.Product;

@Repository(value="sellerRecommendProductDAO")
public class SellerRecommendProductDAOImpl implements SellerRecommendedProductDAO{
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Product> getRecommendedProductList(String emailId) {
		Query query = entityManager.createQuery("select r from RecommendedProductEntity r where r.recommendationStatus = :recommendationStatus and r.sellerEmailId.emailId =:emailId");
		query.setParameter("recommendationStatus", "ACTIVE");
		query.setParameter("emailId", emailId);
		List<RecommendedProductEntity> recommendedProductEntityList= query.getResultList();
		
		List<Product> recommendedProduct = new ArrayList<>();
		for (RecommendedProductEntity recommendedProductEnt : recommendedProductEntityList) {
			ProductEntity product = recommendedProductEnt.getProduct();
			Product p = new Product();
			p.setBrand(product.getBrand());
			p.setCategory(product.getCategory());
			p.setDescription(product.getDescription());
			p.setDiscount(product.getDiscount());
			p.setName(product.getName());
			p.setPrice(product.getPrice());
			p.setProductId(product.getProductId());
			p.setQuantity(product.getQuantity());
			p.setSellerEmailId(emailId);
			DateTimeFormatter df = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
			p.setDateTimeString(df.format(recommendedProductEnt.getRecommendationTimeStamp()));
			recommendedProduct.add(p);
		}
		return recommendedProduct;
	}

	@Override
	public int deleteRecommendedProduct(String emailId, int productId){
		Query query = entityManager.createQuery("update RecommendedProductEntity r set r.recommendationStatus = 'INACTIVE' where r.recommendationStatus = :recommendationStatus and r.sellerEmailId.emailId =:emailId and r.product.productId = :productId");
		query.setParameter("recommendationStatus", "ACTIVE");
		query.setParameter("emailId", emailId);
		query.setParameter("productId", productId);
		return query.executeUpdate();
	}

	@Override
	public void addRecommendedProduct(String emailId, int productId) throws Exception{
		Query query = entityManager.createQuery("select r from RecommendedProductEntity r where r.recommendationStatus = :recommendationStatus and r.sellerEmailId.emailId =:emailId and r.product.productId = :productId");
				query.setParameter("productId", productId);
		query.setParameter("recommendationStatus", "ACTIVE");
		query.setParameter("emailId", emailId);
		List<RecommendedProductEntity> recommendedProductEntityList= query.getResultList();
		int updated = -1;
		if (!recommendedProductEntityList.isEmpty()) {
			throw new Exception("SellerRepository.PRODUCT_ALREADY_RECOMMENDED");
		}
		else {
			query = entityManager.createQuery("update RecommendedProductEntity r set r.recommendationStatus = 'ACTIVE' where r.recommendationStatus = :recommendationStatus and r.sellerEmailId.emailId =:emailId and r.product.productId = :productId");	
			query.setParameter("recommendationStatus", "INACTIVE");
			query.setParameter("emailId", emailId);
			query.setParameter("productId", productId);
			updated = query.executeUpdate();
		}
		if (updated == 0) {
			ProductEntity p = entityManager.find(ProductEntity.class, productId);
			RecommendedProductEntity recommendedProductEntity = new RecommendedProductEntity();
			recommendedProductEntity.setProduct(p);
			recommendedProductEntity.setRecommendationStatus("ACTIVE");
			SellerEntity s = entityManager.find(SellerEntity.class, emailId);
			recommendedProductEntity.setSellerEmailId(s);
			LocalDateTime currentTime = LocalDateTime.now();//check date format in recommendaiton
			recommendedProductEntity.setRecommendationTimeStamp(currentTime);
			entityManager.persist(recommendedProductEntity);
	}
}
}