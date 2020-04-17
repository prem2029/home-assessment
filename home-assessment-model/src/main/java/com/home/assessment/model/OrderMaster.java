package com.home.assessment.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "order_master")
public class OrderMaster implements Serializable {

	private static final long serialVersionUID = 1913253020694973457L;

	@Transient
	public static final String SEQUENCE_NAME = "order_sequence";

	@Id
	long id;

	private String buyerEmailId;

	private double orderAmount;

	private String status;

	private List<OrderedProducts> orderedProductsList;

	private Date createdTs;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBuyerEmailId() {
		return buyerEmailId;
	}

	public void setBuyerEmailId(String buyerEmailId) {
		this.buyerEmailId = buyerEmailId;
	}

	public double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<OrderedProducts> getOrderedProductsList() {
		return orderedProductsList;
	}

	public void setOrderedProductsList(List<OrderedProducts> orderedProductsList) {
		this.orderedProductsList = orderedProductsList;
	}

	public Date getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static String getSequenceName() {
		return SEQUENCE_NAME;
	}
	
	

}
