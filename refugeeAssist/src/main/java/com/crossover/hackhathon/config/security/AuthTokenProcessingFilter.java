package com.crossover.hackhathon.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.crossover.hackhathon.RefugeeAssistConstants;


/**
 * Authentication filter
 * 
 * @author murathacioglu.
 */
@Component
public class AuthTokenProcessingFilter extends OncePerRequestFilter {
	
	
	@Autowired
	private AuthTokenService authTokenService;


	@Override
	protected void doFilterInternal(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse, FilterChain filterChain)
			throws ServletException, IOException {
		
		httpResponse.setHeader("Access-Control-Allow-Origin", "*");
		httpResponse.setHeader("Access-Control-Allow-Methods",
				"GET, POST, PUT, DELETE, OPTIONS");
		// 30 min
		httpResponse.setHeader("Access-Control-Max-Age", "1800");
		httpResponse.setHeader("Access-Control-Allow-Headers",
				"Origin, X-Auth-Token, Content-Type, Accept, X-requested-with");	
		
		String authToken = this.extractAuthTokenFromRequest(httpRequest);
		String userName = authTokenService.getUsernameFromToken(authToken);

		if (userName != null) {
			authToken = RefugeeAssistConstants.prefix + authToken;
			if (authTokenService.validateToken(authToken)) {
				Authentication authentication = authTokenService.retrieve(authToken);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} 
		}		
		filterChain.doFilter(httpRequest, httpResponse);		
	}
	
	/**
	 * Extracts authentication token from HTTP request.
	 * 
	 * @param httpRequest
	 * @return
	 */
	private String extractAuthTokenFromRequest(HttpServletRequest httpRequest) {
		// Get token from header.
		String authToken = httpRequest.getHeader("X-Auth-Token");

		// If token not found, get it from request parameter.
		if (authToken == null) {
			authToken = httpRequest.getParameter("X-Auth-Token");
		}
		return authToken;
	}
	
}

