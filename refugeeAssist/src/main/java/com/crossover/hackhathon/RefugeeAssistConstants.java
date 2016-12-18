package com.crossover.hackhathon;

import org.springframework.http.MediaType;

public class RefugeeAssistConstants {
	

	public static final String PAGE_SIZE = "50";
	public static final int MAX_PAGE_SIZE = 1000;

	public static final String LOGOUT_SUCCESS_MSG = "Logout is successfull.";
	public static final String LOGOUT_ERROR_MSG = "Logout is failed.";
	
    public static final String AUTH_TOKEN_CACHE = "refAssistAuthTokenCache";
	public static final String CITY_CACHE = "cityCache";
	public static final String CITY_DISTRICT_CACHE = "cityDistrictCache";
	public static final String BASE_URL = "http://localhost:4000/api/v1/";

	public static final String prefix="crossover";

	
	public static final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype());
	
	
	private RefugeeAssistConstants(){
		/*
		 * default constructor
		 * */
	}
}
