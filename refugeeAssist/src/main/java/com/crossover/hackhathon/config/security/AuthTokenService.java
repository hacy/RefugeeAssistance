package com.crossover.hackhathon.config.security;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.crossover.hackhathon.RefugeeAssistConstants;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * Service class for creating and storing an authentication token.
 * 
 * @author murathacioglu.
 */
@Service
public class AuthTokenService {

	private static final Logger logger = LoggerFactory.getLogger(AuthTokenService.class);
    
	/** Eviction interval time: 5 minutes */
	private static final long EVICTION_INTERVAL_IN_MILLISECONDS = 5 * 60 * 1000;
	
	private static final Cache petraAuthTokenCache = CacheManager.getInstance()
			.getCache(RefugeeAssistConstants.AUTH_TOKEN_CACHE);
	
    /**
     * Executed every 5 minutes and evicts expired tokens.
     */
    @Scheduled(fixedRate = EVICTION_INTERVAL_IN_MILLISECONDS)
    public void evictExpiredTokens() {
    	logger.info("Evicting expired auth tokens.");
        petraAuthTokenCache.evictExpiredElements();
    }

    /**
     * Validates the authentication token.
     * 
     * @param token
     * @return
     */
    public boolean validateToken(String token) {
        return petraAuthTokenCache.get(token) != null;
    }
    
    public void store(String token, Authentication authentication) {
        petraAuthTokenCache.put(new Element(token, authentication));
    }

    public Authentication retrieve(String token) {
        return (Authentication) petraAuthTokenCache.get(token).getObjectValue();
    }
    
    public boolean remove(String token) {
    	return petraAuthTokenCache.remove(token);
    }
    
	public String createToken(UserDetails userDetails) {		
		StringBuilder tokenBuilder = new StringBuilder();
		tokenBuilder.append(userDetails.getUsername());
		tokenBuilder.append(":");
		tokenBuilder.append(UUID.randomUUID().toString());
		return tokenBuilder.toString();
	}

	/**
	 * Extracts username from authentication token.
	 * 
	 * @param authToken
	 * @return
	 */
	public String getUsernameFromToken(String authToken) {
		if (null == authToken) {
			return null;
		}
		String[] parts = authToken.split(":");
		return parts[0];
	}
	
	public int getTotalTokenCount() {		
		return petraAuthTokenCache.getKeysWithExpiryCheck().size();
	}
    
}

