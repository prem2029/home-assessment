package com.home.assessment.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.home.assessment.dao.AbstractDAO;
import com.home.assessment.model.OrderMaster;

@Component
@Transactional
@Repository("orderMasterDao")
public class OrderMasterDao extends AbstractDAO<OrderMaster> {

	protected OrderMasterDao() {
		super(OrderMaster.class);
	}

	public OrderMaster createOrUpdateOrder(final OrderMaster orderMaster) {
		return save(orderMaster);
	}

	public List<OrderMaster> findOrderListByDateRange(final Date fromDate, final Date toDate) {
		Query query = new Query(
				Criteria.where("createdTs").gte(fromDate).lte(toDate));
		return findAllByQuery(query, OrderMaster.class);

	}

}
