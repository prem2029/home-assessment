package com.home.assessment.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "product_master")
public class ProductMaster implements Serializable {

	private static final long serialVersionUID = 1913253020694973457L;
	
	@Id
	private String sku;	

	private String name;
	
	private double price;
	
	private boolean isActive;

	private Date createdTs;
	
	private Date updatedTs;	
	

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Date getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}

	public Date getUpdatedTs() {
		return updatedTs;
	}

	public void setUpdatedTs(Date updatedTs) {
		this.updatedTs = updatedTs;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
