package com.crossover.hackhathon;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class RefugeeDevEnv implements IRefugeeEnv {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private static final String schema = "Schema";
	
    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.jpa.properties.hibernate.default_schema}")
    private String dbSchema;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;
    
    @Value("${spring.datasource.dataSourceClassName}")
    private String dataSourceClassName;
    
	@Override
	public String getMasterDbUrl() {
		return dbUrl + "?currentSchema=" + dbSchema;
	}

	@Override
	public String getMasterDbSchema() {
		return dbSchema;
	}

	@Override
	public String getMasterDbUsername() {
		return dbUsername;
	}

	@Override
	public String getMasterDbPassword() {
		return dbPassword;
	}
	
	@Override
	public String getDataSourceClassName() {
		return dataSourceClassName;
	}

	@Override
	public String createTenantDB(String schemaName, JpaTransactionManager masterTransactionManager) {
		JdbcTemplate template = new JdbcTemplate(masterTransactionManager.getDataSource());
		try {
			template.execute("CREATE SCHEMA " + schemaName);				
			logger.info(schema + "(" + schemaName + ") creation is successful.");
			return schemaName;
		} catch (Exception ex) {
			logger.error(schema + " (" + schemaName + ") creation is failed.",ex);
		}
		return null;
	}
	
	@Override
	public String createTenantSchemaB(String schemaName, Connection connection) {
		Statement statement;
		try {
			statement = connection.createStatement();
			statement.executeUpdate("CREATE SCHEMA " + schemaName);
			statement.close();
			logger.info(schema+ "(" + schemaName + ") creation is successful.");
			
			return schemaName;
		} catch (SQLException ex) {
			logger.error("Schema (" + schemaName + ") creation is failed.",ex);
		}		
		return null;		
	}	

	@Async
	@Override
	public Future<Boolean> dropTenantDB(String dbName, JpaTransactionManager masterTransactionManager) {
		JdbcTemplate template = new JdbcTemplate(masterTransactionManager.getDataSource());
		try {
			template.execute("DROP DATABASE IF EXISTS " + dbName);
			logger.info("Database (" + dbName + ") drop is successfull.");
		} catch (Exception ex) {
			logger.error("Database (" + dbName + ") drop is failed.",ex);
		}
		return new AsyncResult<Boolean>(true);
	}

}
