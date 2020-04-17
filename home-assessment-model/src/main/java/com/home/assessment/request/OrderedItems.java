package com.home.assessment.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@JsonSerialize
public class OrderedItems {
	
	@JsonProperty
	private String sku;
	
	@JsonProperty
	private int quantity;

	
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
}
