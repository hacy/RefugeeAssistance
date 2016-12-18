package com.crossover.hackhathon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class RefugeeAssistApplication {
	
	
	public static final String API_VERSION = "v1";
	public static final String API_ROOT_PATH = "/api/";
	public static final String API_AUTH_PATH = API_ROOT_PATH + API_VERSION;
    public static final String API_TENANT_PATH = API_ROOT_PATH + API_VERSION ;
    
    public static final String LOGIN_URL = "/login";

    // Spring Boot Actuator services
    public static final String AUTOCONFIG_ENDPOINT = "/autoconfig";
    public static final String BEANS_ENDPOINT = "/beans";
    public static final String CONFIGPROPS_ENDPOINT = "/configprops";
    public static final String ENV_ENDPOINT = "/env";
    public static final String MAPPINGS_ENDPOINT = "/mappings";
    public static final String METRICS_ENDPOINT = "/metrics";
    public static final String SHUTDOWN_ENDPOINT = "/shutdown";
    
	public static String[] actuatorEndpoints() {
		return new String[] { RefugeeAssistApplication.AUTOCONFIG_ENDPOINT, RefugeeAssistApplication.BEANS_ENDPOINT,
				RefugeeAssistApplication.CONFIGPROPS_ENDPOINT, RefugeeAssistApplication.ENV_ENDPOINT,
				RefugeeAssistApplication.MAPPINGS_ENDPOINT, RefugeeAssistApplication.METRICS_ENDPOINT,
				RefugeeAssistApplication.SHUTDOWN_ENDPOINT };
	}
    
	public static void main(String[] args) {
		SpringApplication.run(RefugeeAssistApplication.class, args);
	}
}
