package com.infy.ekart.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.infy.ekart.entity.ProductEntity;
import com.infy.ekart.entity.SellerEntity;
import com.infy.ekart.model.Product;
import com.infy.ekart.model.Seller;


@Repository(value = "sellerDAO")
public class SellerDAOImpl implements SellerDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public String getPasswordOfSeller(String emailId) {
		
		String password = null;
		
		SellerEntity sellerEntity = entityManager.find(SellerEntity.class, emailId);
		
		if (sellerEntity!=null){
			password = sellerEntity.getPassword();
		}
		
		return password;
	}

	@Override
	public Seller getSellerByEmailId(String emailId) throws Exception {
		
		List<Product> sellingProducts = new ArrayList<>();
		
		SellerEntity sellerEntity = entityManager.find(SellerEntity.class, emailId);
		
			Seller seller = new Seller();
			seller.setAddress(sellerEntity.getAddress());
			seller.setEmailId(sellerEntity.getEmailId());
			seller.setName(sellerEntity.getName());
			seller.setPhoneNumber(sellerEntity.getPhoneNumber());
			for (ProductEntity productEntity : sellerEntity.getProductEntities()) {
				Product product = new Product();
				product.setBrand(productEntity.getBrand());
				product.setCategory(productEntity.getCategory());
				product.setDescription(productEntity.getDescription());
				product.setDiscount(productEntity.getDiscount());
				product.setName(productEntity.getName());
				product.setPrice(productEntity.getPrice());
				product.setProductId(productEntity.getProductId());
				product.setQuantity(productEntity.getQuantity());
				
				sellingProducts.add(product);
			}
			
			seller.setProducts(sellingProducts);
		
		return seller;
	}

	@Override
	public Boolean checkAvailabilityOfEmailId(String emailId) {
		
		Boolean flag = false;
		
		Query query = entityManager.createQuery("select s from SellerEntity s where s.emailId = :emailId");
		query.setParameter("emailId", emailId);
		List<SellerEntity> sellerEntities = query.getResultList();

		if(sellerEntities.isEmpty())
			flag = true;

		return flag;
	}

	@Override
	public Boolean checkAvailabilityOfPhoneNumber(String phoneNumber) {
		
		Boolean flag = false;
		
		Query query = entityManager.createQuery("select s from SellerEntity s where s.phoneNumber = :phoneNumber");
		query.setParameter("phoneNumber", phoneNumber);
		List<SellerEntity> sellerEntities = query.getResultList();

		if(sellerEntities.isEmpty())
			flag = true;

		return flag;
	}

	@Override
	public String registerNewSeller(Seller seller) {
		
		String registeredWithEmailId = null;
		
		SellerEntity sellerEntity = new SellerEntity();
		
		sellerEntity.setAddress(seller.getAddress());
		sellerEntity.setEmailId(seller.getEmailId().toLowerCase());
		sellerEntity.setName(seller.getName());
		sellerEntity.setPassword(seller.getPassword());
		sellerEntity.setPhoneNumber(seller.getPhoneNumber());
		sellerEntity.setProductEntities(null);
		
		entityManager.persist(sellerEntity);
		
		registeredWithEmailId = sellerEntity.getEmailId();

		return registeredWithEmailId;
	}
	
	@Override
	public Seller getSellerByPhoneNumber(String phoneNumber) {
		
		Seller seller=null;

		Query query = entityManager.createQuery("select s from SellerEntity s where s.phoneNumber = :phoneNumber");
		query.setParameter("phoneNumber", phoneNumber);
		List<SellerEntity> sellerEntities = query.getResultList();
		if(sellerEntities.isEmpty())
			return null;
		
		SellerEntity sellerEntity = sellerEntities.get(0);
		
		seller = new Seller();
		seller.setAddress(sellerEntity.getAddress());
		seller.setEmailId(sellerEntity.getEmailId());
		seller.setName(sellerEntity.getName());
		seller.setPhoneNumber(sellerEntity.getPhoneNumber());
			
		return seller;
	}
	
	@Override
	public void updateProfile(Seller seller) {
		
		SellerEntity sellerEntity = entityManager.find(SellerEntity.class, seller.getEmailId());
		sellerEntity.setAddress(seller.getAddress());
		sellerEntity.setName(seller.getName());
		sellerEntity.setPhoneNumber(seller.getPhoneNumber());

	}
	
	@Override
	public void changePassword(String sellerEmailId, String newHashedPassword) {
		
		SellerEntity sellerEntity = entityManager.find(SellerEntity.class, sellerEmailId);		
		sellerEntity.setPassword(newHashedPassword); 
		
	}
}
