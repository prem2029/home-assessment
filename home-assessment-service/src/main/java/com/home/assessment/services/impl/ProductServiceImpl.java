package com.home.assessment.services.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.home.assessment.exception.AssessmentException;
import com.home.assessment.model.ProductMaster;
import com.home.assessment.repository.ProductMasterDao;
import com.home.assessment.request.ProductCreateRequest;
import com.home.assessment.request.ProductDeleteRequest;
import com.home.assessment.request.ProductUpdateRequest;
import com.home.assessment.services.ProductService;
import com.home.assessment.util.Constants;

/**
 * Product information
 */
@Component
@Scope("prototype")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMasterDao productMasterDao;

	@Override
	public Map<String, Object> createProduct(final ProductCreateRequest createRequest) throws AssessmentException {
		try {
			Map<String, Object> response = new HashMap<String, Object>();

			if (createRequest.getSku() != null && !createRequest.getSku().isEmpty()) {
				ProductMaster productMaster = productMasterDao.findById(createRequest.getSku());

				// If the product is in deleted status, response will be product already exists
				// and no action is performed.
				if (productMaster != null) {
					response.put(Constants.STATUS, Constants.PRODUCT_ALREADY_EXISTS);
				} else {
					ProductMaster newProductMaster = new ProductMaster();
					newProductMaster.setActive(true);
					newProductMaster.setName(createRequest.getName());
					newProductMaster.setSku(createRequest.getSku());
					newProductMaster.setPrice(createRequest.getPrice());
					Calendar calendar = Calendar.getInstance();
					Date now = calendar.getTime();
					newProductMaster.setCreatedTs(now);

					productMasterDao.createOrUpdateProduct(newProductMaster);
					response.put(Constants.STATUS, Constants.PRODUCT_CREATED);
				}
			}

			return response;
		} catch (Exception ex) {
			throw new AssessmentException(ex.getMessage());
		}

	}

	@Override
	public Map<String, Object> updateProduct(final ProductUpdateRequest updateRequest) throws AssessmentException {
		try {
			Map<String, Object> response = new HashMap<String, Object>();

			if (updateRequest.getSku() != null && !updateRequest.getSku().isEmpty()) {
				ProductMaster productMaster = productMasterDao.findById(updateRequest.getSku());

				if (productMaster != null) {
					productMaster.setName(updateRequest.getName());
					productMaster.setPrice(updateRequest.getPrice());
					Calendar calendar = Calendar.getInstance();
					Date now = calendar.getTime();
					productMaster.setUpdatedTs(now);
					productMasterDao.createOrUpdateProduct(productMaster);
					response.put(Constants.STATUS, Constants.PRODUCT_UPDATED);
				} else {
					response.put(Constants.STATUS, Constants.PRODUCT_INVALID);
				}
			}
			return response;
		} catch (Exception ex) {
			throw new AssessmentException(ex.getMessage());
		}
	}

	@Override
	public Map<String, Object> deleteProduct(ProductDeleteRequest updateRequest) throws AssessmentException {
		try {
			Map<String, Object> response = new HashMap<String, Object>();

			if (updateRequest.getSku() != null && !updateRequest.getSku().isEmpty()) {
				ProductMaster productMaster = productMasterDao.findById(updateRequest.getSku());
				if (productMaster != null) {
					productMaster.setActive(false);
					Calendar calendar = Calendar.getInstance();
					Date now = calendar.getTime();
					productMaster.setUpdatedTs(now);
					productMasterDao.createOrUpdateProduct(productMaster);
					response.put(Constants.STATUS, Constants.PRODUCT_DELETED);
				} else {
					response.put(Constants.STATUS, Constants.PRODUCT_INVALID);
				}
			}
			return response;
		} catch (Exception ex) {
			throw new AssessmentException(ex.getMessage());
		}
	}

	@Override
	public List<ProductMaster> getActiveProductList() throws AssessmentException {
		try {
			return productMasterDao.findActiveProductList();

		} catch (Exception ex) {
			throw new AssessmentException(ex.getMessage());
		}
	}
}
