package com.infy.ekart.dao;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.infy.ekart.entity.CustRecProdEntity;
import com.infy.ekart.entity.ProductEntity;
import com.infy.ekart.model.CustRecProd;
import com.infy.ekart.model.Product;
@Repository(value = "customerProductDAO")
public class CustomerProductDAOImpl implements CustomerProductDAO {
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Product> getAllProducts() {

		Query query = entityManager.createQuery("select p from ProductEntity p");
		
		List<ProductEntity> productEntityList= query.getResultList();
		
		List<Product> listOfProducts = new ArrayList<Product>();
		for (ProductEntity productEntity : productEntityList) {
			Product product = new Product();
			product.setBrand(productEntity.getBrand());
			product.setCategory(productEntity.getCategory());
			product.setDescription(productEntity.getDescription());
			product.setName(productEntity.getName());
			product.setPrice(productEntity.getPrice());
			product.setProductId(productEntity.getProductId());
			product.setQuantity(productEntity.getQuantity());
			product.setDiscount(productEntity.getDiscount());

			listOfProducts.add(product);
		}
		return listOfProducts;
	}
	
	@Override
	public List<Product> getRecProducts() {

		Query query = entityManager.createQuery("select c from CustRecProdEntity c where c.recStatus='ACTIVE' order by c.recTimestamp desc");
		
		List<CustRecProdEntity> CustRecProdEntityList= query.getResultList();
		
		List<Product> listOfProducts = new ArrayList<Product>();
		
		Set<Integer> dupSet = new HashSet<Integer>();
		
		for (CustRecProdEntity custRecProdEntity : CustRecProdEntityList) {
			
			CustRecProd custRecProd = new CustRecProd();
			custRecProd.setRecTimestamp(custRecProdEntity.getRecTimestamp());
			
			ProductEntity productEntity = custRecProdEntity.getProduct();
			
			if(!dupSet.contains(productEntity.getProductId())) {
				dupSet.add(productEntity.getProductId());
				
			Product product = new Product();
			product.setBrand(productEntity.getBrand());
			product.setCategory(productEntity.getCategory());
			product.setDescription(productEntity.getDescription());
			product.setName(productEntity.getName());
			product.setPrice(productEntity.getPrice());
			product.setProductId(productEntity.getProductId());
			product.setQuantity(productEntity.getQuantity());
			product.setDiscount(productEntity.getDiscount());
			product.setRecTimestamp(custRecProdEntity.getRecTimestamp());
			DateTimeFormatter df = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
			product.setDateTimeString(df.format(custRecProdEntity.getRecTimestamp()));

			listOfProducts.add(product);
			}
		}
		return listOfProducts;
	}
	
}
