package com.crossover.hackhathon.controller;
/**
 * Transfer class for authentication tokens.
 * 
 * @author mhacioglu
 */
public class AuthTokenTransport {

	private String token;

	public AuthTokenTransport() {
		/*
		 * default constructor
		 * */
	}

	public AuthTokenTransport(String token) {
		this.token = token;
	}
	
	public String getToken() {
		return this.token;
	}
	
	
}
