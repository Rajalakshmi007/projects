package com.product.model;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Product {
	
	String productId;
	String productName;
	Double productPrice;
	String productDesc;
	int quantity;
	
	boolean editable;
	
	public Product() {
		
	}
	
	public Product(String productId, String productName, Double price, String productDesc, int quantity) {
		super();
		this.productId = productId;
		this.productName = productName;
		productPrice = price;
		this.productDesc = productDesc;
		this.quantity = quantity;
	}
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

}
