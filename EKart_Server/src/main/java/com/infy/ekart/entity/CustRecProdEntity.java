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
public class CustRecProdEntity {

	@Id
	@Column(name="RECOMMENDATION_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer recId;

	@Column(name="RECOMMENDATION_TIMESTAMP")
	private LocalDateTime recTimestamp;
	
	@Column(name="RECOMMENDATION_STATUS")
	private String recStatus;
	
	@Column(name="SELLER_EMAIL_ID")
	private String sellerEmailId;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="PRODUCT_ID")
	private ProductEntity product;

	public Integer getRecId() {
		return recId;
	}

	public void setRecId(Integer recId) {
		this.recId = recId;
	}

	public LocalDateTime getRecTimestamp() {
		return recTimestamp;
	}

	public void setRecTimestamp(LocalDateTime recTimestamp) {
		this.recTimestamp = recTimestamp;
	}

	public String getRecStatus() {
		return recStatus;
	}

	public void setRecStatus(String recStatus) {
		this.recStatus = recStatus;
	}

	public String getSellerEmailId() {
		return sellerEmailId;
	}

	public void setSellerEmailId(String sellerEmailId) {
		this.sellerEmailId = sellerEmailId;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	
}
