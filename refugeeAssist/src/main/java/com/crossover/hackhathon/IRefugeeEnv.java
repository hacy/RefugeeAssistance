package com.crossover.hackhathon;

import java.sql.Connection;
import java.util.concurrent.Future;

import org.springframework.orm.jpa.JpaTransactionManager;

/**
 * 
 * @author Murat Hacioglu
 */
public interface IRefugeeEnv {

	public String getMasterDbUrl();

	public String getMasterDbSchema();

	public String getMasterDbUsername();

	public String getMasterDbPassword();
	
	
	public String getDataSourceClassName();
	
	
	public String createTenantDB(String schemaName, JpaTransactionManager masterTransactionManager);
	
	public String createTenantSchemaB(String schemaName, Connection connection);
	
	public Future<Boolean> dropTenantDB(String schemaName, JpaTransactionManager masterTransactionManager);
	
}
