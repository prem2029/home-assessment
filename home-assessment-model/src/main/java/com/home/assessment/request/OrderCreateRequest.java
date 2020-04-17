package com.home.assessment.request;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class OrderCreateRequest {

	public String getBuyerEmailId() {
		return buyerEmailId;
	}

	public void setBuyerEmailId(String buyerEmailId) {
		this.buyerEmailId = buyerEmailId;
	}

	public List<OrderedItems> getItems() {
		return items;
	}

	public void setItems(List<OrderedItems> items) {
		this.items = items;
	}

	@JsonProperty
	private String buyerEmailId;
	
	@JsonProperty
	private List<OrderedItems> items = new ArrayList<OrderedItems>();

}
