package com.crossover.hackhathon.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.crossover.hackhathon.RefugeeAssistApplication;
import com.crossover.hackhathon.RefugeeAssistConstants;
import com.crossover.hackhathon.config.security.AuthTokenService;


/**
 * API authentication end point.
 * 
 * @author mhacioglu team.
 */
@RestController
@RequestMapping(value = RefugeeAssistApplication.API_AUTH_PATH, 
				produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

	private static final Logger logger 	 = LoggerFactory.getLogger(AuthenticationController.class);
	
	@Autowired
	private AuthTokenService authTokenService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService petraUserDetailsService;
	
	

	@RequestMapping(value = RefugeeAssistApplication.LOGIN_URL, method = RequestMethod.POST)
	@ResponseBody
	public AuthTokenTransport login(@RequestBody LoginTransport loginTransport) {
		logger.info("User is logging in...");
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				loginTransport.getUsername(), loginTransport.getPassword());
		try{
		Authentication authentication = authenticationManager.authenticate(authenticationToken);
		if(authentication.isAuthenticated()){	
			SecurityContextHolder.getContext().setAuthentication(authentication);
			UserDetails userDetails = petraUserDetailsService.loadUserByUsername(loginTransport.getUsername());
			String authToken = authTokenService.createToken(userDetails);
			authTokenService.store(RefugeeAssistConstants.prefix + authToken, authentication);
			logger.info("User is successfully logged in.");
			return new AuthTokenTransport(authToken);
		}
		}catch(AuthenticationException e){
			logger.error("User authentication error logged in.",e);
		}
		return null;
	}
	
}
