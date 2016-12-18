package com.crossover.hackhathon.config;

import java.util.Map;
import java.util.Properties;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.crossover.hackhathon.IRefugeeEnv;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import liquibase.integration.spring.SpringLiquibase;

@Configuration
@EnableConfigurationProperties(JpaProperties.class)
@EnableJpaRepositories(
      entityManagerFactoryRef = "masterEntityManager",
      transactionManagerRef = "masterTransactionManager",
      basePackages = {"com.crossover.hackhathon.repository"})
@EnableTransactionManagement
public class RefugeeJpaConfiguration {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
    @Value("${liquibase.context}")
    private String liquibaseContext;
    
    @Autowired
    private IRefugeeEnv refEnv;
    
    @Inject
    private DataSource dataSource;
    
    @Inject
    private JpaProperties jpaProperties;    
    
    
    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        logger.debug("Configuring datasource {} {} {}", 
        		refEnv.getDataSourceClassName(), 
        		refEnv.getMasterDbUrl(), 
        		refEnv.getMasterDbUsername());
        HikariConfig config = new HikariConfig();
        config.setMaximumPoolSize(2);
        config.setDataSourceClassName(refEnv.getDataSourceClassName());
        config.addDataSourceProperty("url", refEnv.getMasterDbUrl());
        config.addDataSourceProperty("user", refEnv.getMasterDbUsername());
        config.addDataSourceProperty("password", refEnv.getMasterDbPassword());
        return new HikariDataSource(config);
    }

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase sl = new SpringLiquibase();
        sl.setDataSource(dataSource);
        sl.setContexts(liquibaseContext);
        sl.setChangeLog("classpath:db.changelog.xml");
        sl.setDefaultSchema(refEnv.getMasterDbSchema());
        sl.setShouldRun(true);
        return sl;
    }

	@Bean(name = "masterEntityManager")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan("com.crossover.hackhathon.model");
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalJpaProperties());

		em.setPersistenceUnitName("master");
		return em;
	}

	@Bean(name = "masterTransactionManager")
	public JpaTransactionManager transactionManager(EntityManagerFactory masterEntityManager) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(masterEntityManager);
		return transactionManager;
	}
	
	private Properties additionalJpaProperties() {
		Properties properties = new Properties();
		for (Map.Entry<String, String> entry : jpaProperties.getHibernateProperties(dataSource).entrySet()) {
			properties.setProperty(entry.getKey(), entry.getValue());
		}
		return properties;
	}

}

