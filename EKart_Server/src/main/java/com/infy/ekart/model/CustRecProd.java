package com.infy.ekart.model;

import java.time.LocalDateTime;

public class CustRecProd {
	
	private Integer recId;
	private LocalDateTime recTimestamp;
	private String recStatus;
	private String sellerEmailId;
	private Product product;
	
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
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
}
