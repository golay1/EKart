package com.infy.ekart.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="EK_RECOMMENDED_PRODUCT")
public class RecommendedProductEntity {
	
	@Id
	@Column(name="RECOMMENDATION_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int recommendationId;
	
	@Column(name="RECOMMENDATION_TIMESTAMP")
	private LocalDateTime recommendationTimeStamp;
	
	@Column(name="RECOMMENDATION_STATUS")
	private String recommendationStatus;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="PRODUCT_ID")
	private ProductEntity product;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="SELLER_EMAIL_ID")
	private SellerEntity sellerEmailId;

	public int getRecommendationId() {
		return recommendationId;
	}

	public void setRecommendationId(int recommendationId) {
		this.recommendationId = recommendationId;
	}

	public LocalDateTime getRecommendationTimeStamp() {
		return recommendationTimeStamp;
	}

	public void setRecommendationTimeStamp(LocalDateTime currentTime) {
		this.recommendationTimeStamp = currentTime;
	}

	public String getRecommendationStatus() {
		return recommendationStatus;
	}

	public void setRecommendationStatus(String recommendationStatus) {
		this.recommendationStatus = recommendationStatus;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public SellerEntity getSellerEmailId() {
		return sellerEmailId;
	}

	public void setSellerEmailId(SellerEntity sellerEmailId) {
		this.sellerEmailId = sellerEmailId;
	}
	
	
}
