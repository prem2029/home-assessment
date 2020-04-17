package com.home.assessment.repository;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.home.assessment.dao.AbstractDAO;
import com.home.assessment.model.ProductMaster;
import com.home.assessment.util.Constants;


@Component
@Transactional
@Repository("productMasterDao")
public class ProductMasterDao extends AbstractDAO<ProductMaster> {

	protected ProductMasterDao() {
		super(ProductMaster.class);
	}

	public ProductMaster createOrUpdateProduct(final ProductMaster productMaster) {
		return save(productMaster);
	}

	public ProductMaster findById(final String Id) {
		return fetchById(Id);
	}

	public List<ProductMaster> findActiveProductList() {
		Query query = new Query();
		query.addCriteria(Criteria.where(Constants.IS_ACTIVE).is(true));
		return findAllByQuery(query, ProductMaster.class);
	}

}
