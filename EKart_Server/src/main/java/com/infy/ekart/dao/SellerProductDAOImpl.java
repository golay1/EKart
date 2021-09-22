package com.infy.ekart.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.infy.ekart.entity.ProductCategoryEntity;
import com.infy.ekart.entity.ProductEntity;
import com.infy.ekart.entity.SellerEntity;
import com.infy.ekart.model.Product;

@Repository(value = "sellerProductDAO")
public class SellerProductDAOImpl implements SellerProductDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public Integer addNewProduct(Product product) {
		
		ProductEntity newProduct = new ProductEntity();
		newProduct.setBrand(product.getBrand());
		newProduct.setCategory(product.getCategory());
		newProduct.setDescription(product.getDescription());
		newProduct.setDiscount(product.getDiscount());
		newProduct.setName(product.getName());
		newProduct.setPrice(product.getPrice());
		newProduct.setQuantity(product.getQuantity());
		
		SellerEntity sellerEntity = entityManager.find(SellerEntity.class, product.getSellerEmailId());
		
		sellerEntity.getProductEntities().add(newProduct);
		
		entityManager.persist(sellerEntity);
		ProductEntity lastProduct = sellerEntity.getProductEntities().get(sellerEntity.getProductEntities().size()-1);
		return lastProduct.getProductId();
		
	}
	
	@Override
	public Product modifyProductDetails(Product product) {
		
			Query query = entityManager.createQuery("select p from ProductEntity p where p.productId = :productId");
			query.setParameter("productId", product.getProductId());
			
			ProductEntity productEntity = (ProductEntity) query.getResultList().get(0);
			
			productEntity.setDescription(product.getDescription());
			productEntity.setDiscount(product.getDiscount());
			productEntity.setName(product.getName());
			productEntity.setPrice(product.getPrice());
			productEntity.setQuantity(product.getQuantity());
			
		return product;
		
	}
	
	@Override
	public Integer removeProduct(String sellerEmailId, Integer productId) {

		SellerEntity sellerEntity= entityManager.find(SellerEntity.class, sellerEmailId);
		
		List<ProductEntity> productEntities = sellerEntity.getProductEntities();
		
		List<ProductEntity> updatedProductEntities = new ArrayList<>(); 
		
		for (ProductEntity productEntity : productEntities) {
			if(!productId.equals(productEntity.getProductId())){
				updatedProductEntities.add(productEntity);
			}
		}
		
		sellerEntity.setProductEntities(updatedProductEntities);
		
		return productId;
	}
	
	@Override
	public List<String> getProductCategoryList() {
		
		Query query = entityManager.createQuery("select p from ProductCategoryEntity p");
		List<ProductCategoryEntity> productCategoryEntityList= query.getResultList();
		
		List<String> productCategories = new ArrayList<>();
		for (ProductCategoryEntity productCategoryEntity : productCategoryEntityList) {
			productCategories.add(productCategoryEntity.getCategory());
		}
		return productCategories;
	}

}
