package com.home.assessment.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;


/**
 * DB Configuration for the MongoDB connection.
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.home")
public class DataConfig extends AbstractMongoConfiguration {

	@Value("${spring.data.mongodb.host}")
	private String host;
	
	@Value("${spring.data.mongodb.port}")
	private int port;
	
	@Value("${spring.data.mongodb.username}")
	private String userName;
	
	@Value("${spring.data.mongodb.password}")
	private String password;
	
	@Value("${spring.data.mongodb.database}")
	private String db;
	
	@Override
	protected String getDatabaseName() {
	    return db;
	}
	
	/**
	 * Mongo template for DB Process.
	 * 
	 * @param mongoDbFactory
	 * @param context
	 * @return
	 */
	@Bean
	public MongoTemplate mongoTemplate(final MongoDbFactory mongoDbFactory, final MongoMappingContext context) {
		MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory), context);
		converter.setTypeMapper(new DefaultMongoTypeMapper(null));
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory, converter);
		return mongoTemplate;
	}

	/**
	 * Establishing MongoDB connection by credentials.
	 */
	@Override
	@Bean
	public MongoClient mongoClient() {
		MongoCredential credential = MongoCredential.createCredential(userName, db, password.toCharArray());
		return new MongoClient(new ServerAddress(host, port), Arrays.asList(credential));
	}

}
