package com.home.assessment.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@JsonSerialize
public class OrderDateRangeRequest {

	@JsonProperty
	private Date fromDate;
	
	@JsonProperty
	private Date toDate;

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
	

}
