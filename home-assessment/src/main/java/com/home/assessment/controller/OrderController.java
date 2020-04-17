package com.home.assessment.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.home.assessment.exception.AssessmentException;
import com.home.assessment.request.OrderCreateRequest;
import com.home.assessment.request.OrderDateRangeRequest;
import com.home.assessment.services.OrderService;
import com.home.assessment.util.Constants;
import com.home.assessment.util.ServiceResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class OrderController {

	@Autowired
	private OrderService orderService;

	/**
	 * Create Order.
	 */
	@PostMapping(value = ResourceConstants.ORDER_RESOURCE + "/create", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ServiceResponse createOrder(@Valid @RequestBody OrderCreateRequest createRequest) {
		ServiceResponse serviceResponse = new ServiceResponse();
		try {
			// Create Order
			Map<String, Object> response = orderService.createOrder(createRequest);

			// If the code execution successful, code will be "SUCCESS", and exception in the process code will be set to "FAILED"
			serviceResponse.setCode(response.get(Constants.STATUS) != null ? Constants.SUCCESS : Constants.FAILED);
			serviceResponse.setResponse(response);
		} catch (AssessmentException ae) {
			log.error("OrderController::createOrder:=>" + ae.getMessage());
		} catch (Exception e) {
			log.error("ProductController::createOrder:=>" + e.getMessage());
		}
		return serviceResponse;
	}
	
	/**
	 * Get orders by date range
	 */
	@PostMapping(value = ResourceConstants.ORDER_RESOURCE + "/getOrdersByDateRange", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ServiceResponse getOrdersByDateRange(@Valid @RequestBody OrderDateRangeRequest dateRangeRequest) {
		ServiceResponse serviceResponse = new ServiceResponse();
		try {
			serviceResponse.setResponse(orderService.getOrderByDateRange(dateRangeRequest));

			serviceResponse.setCode(Constants.SUCCESS);
		} catch (AssessmentException ae) {
			log.error("OrderController::createOrder:=>" + ae.getMessage());
		} catch (Exception e) {
			log.error("ProductController::createOrder:=>" + e.getMessage());
		}
		return serviceResponse;
	}
}