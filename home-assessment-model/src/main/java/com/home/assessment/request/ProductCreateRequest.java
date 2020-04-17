package com.home.assessment.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@JsonSerialize
public class ProductCreateRequest {

	@JsonProperty
	private String name;
	
	@JsonProperty
	private String sku;
	
	@JsonProperty
	private double price;

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

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}
	
	

}
