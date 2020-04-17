package com.home.assessment.services;

import java.util.List;
import java.util.Map;

import com.home.assessment.exception.AssessmentException;
import com.home.assessment.model.OrderMaster;
import com.home.assessment.request.OrderCreateRequest;
import com.home.assessment.request.OrderDateRangeRequest;

public interface OrderService {

	Map<String, Object> createOrder(final OrderCreateRequest createRequest) throws AssessmentException;
	
	List<OrderMaster> getOrderByDateRange(final OrderDateRangeRequest dateRangeRequest)
			throws AssessmentException;

}
