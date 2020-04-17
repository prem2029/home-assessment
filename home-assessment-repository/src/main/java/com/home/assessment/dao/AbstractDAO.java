package com.home.assessment.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * AbstractDao is used as abstract class for having all 
 * the static Dao methods for using it in all the implementation.
 */
@Component
@Repository
public abstract class AbstractDAO<T> {

	@Autowired
	MongoTemplate mongoTemplate;

	protected Class<T> entityClass;

	protected Query query;

	/**
	 * constructor to get the entity class
	 *
	 */
	protected AbstractDAO(final Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	

	/**
	 * find All method to get all documents from a collection.
	 *
	 */
	protected List<T> findAll() {
		return mongoTemplate.findAll(entityClass);
	}
	

	/**
	 * Getting the entity object by passing the key
	 *
	 * @param id
	 * @return
	 */
	protected T fetchById(final String id) {
		return mongoTemplate.findById(id, entityClass);
	}

	
	/**
	 * This method is used to save object to an collection.
	 *
	 * @param object
	 */
	protected T save(final T object) {
		mongoTemplate.save(object);
		return object;
	}

	/**
	 * This method is used to execute command to invoke 
	 * db script
	 * 
	 * @param command
	 */
	public void runCommand(final String command) {
		mongoTemplate.executeCommand(command);
	}
	
	/**
	 * Getting the entity object by passing the key
	 *
	 * @param id
	 * @return list
	 */
	protected List<T> findAllByQuery(final Query query,final Class<T> entityClass) {
		return mongoTemplate.find(query, entityClass);
	}


}