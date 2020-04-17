package com.home.assessment.services;

import java.util.List;
import java.util.Map;

import com.home.assessment.exception.AssessmentException;
import com.home.assessment.model.ProductMaster;
import com.home.assessment.request.ProductCreateRequest;
import com.home.assessment.request.ProductDeleteRequest;
import com.home.assessment.request.ProductUpdateRequest;

public interface ProductService {
	
	List<ProductMaster> getActiveProductList() throws AssessmentException;

	Map<String, Object> createProduct(final ProductCreateRequest createRequest) throws AssessmentException;

	Map<String, Object> updateProduct(final ProductUpdateRequest updateRequest) throws AssessmentException;

	Map<String, Object> deleteProduct(final ProductDeleteRequest updateRequest) throws AssessmentException;

}
