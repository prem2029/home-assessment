package com.home.assessment.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.home.assessment.exception.AssessmentException;
import com.home.assessment.request.ProductCreateRequest;
import com.home.assessment.request.ProductDeleteRequest;
import com.home.assessment.request.ProductUpdateRequest;
import com.home.assessment.services.ProductService;
import com.home.assessment.util.Constants;
import com.home.assessment.util.ServiceResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ProductController {

	@Autowired
	private ProductService productService;

	/**
	 * Create Product.
	 */
	@PostMapping(value = ResourceConstants.PRODUCT_RESOURCE
			+ "/create", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ServiceResponse createProduct(@Valid @RequestBody ProductCreateRequest createRequest) {
		ServiceResponse serviceResponse = new ServiceResponse();

		try {

			// Create Product
			Map<String, Object> response = productService.createProduct(createRequest);

			// If the code execution successful, code will be "SUCCESS", and exception in
			// the process code will be set to "FAILED"
			serviceResponse.setCode(response.get(Constants.STATUS) != null ? Constants.SUCCESS : Constants.FAILED);
			serviceResponse.setResponse(response);

		} catch (AssessmentException ae) {
			log.error("ProductController::createProduct:=>" + ae.getMessage());
		} catch (Exception e) {
			log.error("ProductController::createProduct:=>" + e.getMessage());
		}
		return serviceResponse;
	}

	/**
	 * Update Product by sku
	 */
	@PostMapping(value = ResourceConstants.PRODUCT_RESOURCE
			+ "/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ServiceResponse updateProduct(@Valid @RequestBody ProductUpdateRequest updateRequest) {
		ServiceResponse serviceResponse = new ServiceResponse();

		try {

			// Update Product
			Map<String, Object> response = productService.updateProduct(updateRequest);

			// If the code execution successful, code will be "SUCCESS", and exception in
			// the process code will be set to "FAILED"
			serviceResponse.setCode(response.get(Constants.STATUS) != null ? Constants.SUCCESS : Constants.FAILED);
			serviceResponse.setResponse(response);

		} catch (AssessmentException ae) {
			log.error("ProductController::updateProduct:=>" + ae.getMessage());
		} catch (Exception e) {
			log.error("ProductController::updateProduct:=>" + e.getMessage());
		}
		return serviceResponse;
	}

	/**
	 * Delete Product by sku
	 */
	@DeleteMapping(value = ResourceConstants.PRODUCT_RESOURCE
			+ "/delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ServiceResponse updateProduct(@Valid @RequestBody ProductDeleteRequest deleteRequest) {
		ServiceResponse serviceResponse = new ServiceResponse();

		try {
			// Soft Delete the product
			Map<String, Object> response = productService.deleteProduct(deleteRequest);

			// If the code execution successful, code will be "SUCCESS", and exception in
			// the process code will be set to "FAILED"
			serviceResponse.setCode(response.get(Constants.STATUS) != null ? Constants.SUCCESS : Constants.FAILED);
			serviceResponse.setResponse(response);
		} catch (AssessmentException ae) {
			log.error("ProductController::updateProduct:=>" + ae.getMessage());
		} catch (Exception e) {
			log.error("ProductController::updateProduct:=>" + e.getMessage());
		}
		return serviceResponse;
	}

	/**
	 * Get active product list
	 */
	@GetMapping(value = ResourceConstants.PRODUCT_RESOURCE + "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ServiceResponse getProductList() {
		ServiceResponse serviceResponse = new ServiceResponse();
		try {
			// Active product list
			serviceResponse.setResponse(productService.getActiveProductList());
			serviceResponse.setCode(Constants.SUCCESS);

		} catch (AssessmentException ae) {
			log.error("ProductController::getProductList:=>" + ae.getMessage());
		} catch (Exception e) {
			log.error("ProductController::getProductList:=>" + e.getMessage());
		}
		return serviceResponse;
	}
}
