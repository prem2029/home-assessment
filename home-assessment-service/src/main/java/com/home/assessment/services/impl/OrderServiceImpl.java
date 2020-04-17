package com.home.assessment.services.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.home.assessment.dao.SequenceGeneratorDao;
import com.home.assessment.exception.AssessmentException;
import com.home.assessment.model.OrderMaster;
import com.home.assessment.model.OrderedProducts;
import com.home.assessment.model.ProductMaster;
import com.home.assessment.repository.OrderMasterDao;
import com.home.assessment.repository.ProductMasterDao;
import com.home.assessment.request.OrderCreateRequest;
import com.home.assessment.request.OrderDateRangeRequest;
import com.home.assessment.request.OrderedItems;
import com.home.assessment.services.OrderService;
import com.home.assessment.util.Constants;
import com.home.assessment.util.OrderStatus;

/**
 * Order processing
 */
@Component
@Scope("prototype")
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMasterDao orderMasterDao;

	@Autowired
	private ProductMasterDao productMasterDao;

	@Autowired
	private SequenceGeneratorDao sequenceGeneratorDao;

	@Override
	public Map<String, Object> createOrder(final OrderCreateRequest createRequest) throws AssessmentException {
		try {
			Map<String, Object> response = new HashMap<String, Object>();

			// Checking items exists in the order request
			if (createRequest.getItems() != null && !createRequest.getItems().isEmpty()) {
				OrderMaster orderMaster = new OrderMaster();
				orderMaster.setStatus(OrderStatus.OPEN);
				orderMaster.setBuyerEmailId(createRequest.getBuyerEmailId());

				List<OrderedProducts> itemList = new ArrayList<OrderedProducts>();

				// Getting the unit price and calculating the total price of each ordered product from the database.
				// Calculating the total order amount 
				createRequest.getItems().forEach((OrderedItems item) -> {
					ProductMaster productMaster = productMasterDao.findById(item.getSku());
					if (productMaster != null) {
						OrderedProducts product = new OrderedProducts();
						product.setSku(item.getSku());
						product.setName(productMaster.getName());
						product.setUnitPrice(productMaster.getPrice());
						product.setQuantity(item.getQuantity());
						Double totalPrice = productMaster.getPrice() * item.getQuantity();
						BigDecimal bd = new BigDecimal(totalPrice).setScale(2, RoundingMode.HALF_UP);
						orderMaster.setOrderAmount(orderMaster.getOrderAmount() + bd.doubleValue());
						product.setTotalPrice(bd.doubleValue());
						itemList.add(product);
					}
				});

				// If the item is not exists in the database, order is not processed
				if (itemList != null && !itemList.isEmpty()) {
					// Generating the order number from the sequence
					orderMaster.setId(sequenceGeneratorDao.generateSequence(OrderMaster.SEQUENCE_NAME));
					orderMaster.setOrderedProductsList(itemList);

					Calendar calendar = Calendar.getInstance();
					Date now = calendar.getTime();
					orderMaster.setCreatedTs(now);
					
					// Save order details in the database
					orderMasterDao.createOrUpdateOrder(orderMaster);

					response.put(Constants.STATUS, Constants.ORDER_CREATED);
					response.put(Constants.ORDER_ID, orderMaster.getId());
				} else {
					response.put(Constants.STATUS, Constants.ORDER_NOT_CREATED);
				}
			} else {
				response.put(Constants.STATUS, Constants.ORDER_NOT_CREATED);
			}
			return response;

		} catch (Exception ex) {
			throw new AssessmentException(ex.getMessage());
		}

	}

	@Override
	public List<OrderMaster> getOrderByDateRange(OrderDateRangeRequest dateRangeRequest) throws AssessmentException {
		try {
			// Order information is generated for requested date range
			return orderMasterDao.findOrderListByDateRange(dateRangeRequest.getFromDate(),
					dateRangeRequest.getToDate());

		} catch (Exception ex) {
			throw new AssessmentException(ex.getMessage());
		}
	}
}
