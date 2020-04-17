package com.home.assessment.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ServiceResponse {

	private Object response;
	private String code = "FAILED";
	private String responseDateTime;
	
	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getResponseDateTime() {
		return responseDateTime;
	}

	public void setResponseDateTime(String responseDateTime) {
		this.responseDateTime = responseDateTime;
	}

	public static SimpleDateFormat getDateFormat() {
		return dateFormat;
	}

	public static void setDateFormat(SimpleDateFormat dateFormat) {
		ServiceResponse.dateFormat = dateFormat;
	}

	private static final String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_TIME_PATTERN);

	public ServiceResponse() {
		responseDateTime = dateFormat.format(Calendar.getInstance().getTime());
	}

}
