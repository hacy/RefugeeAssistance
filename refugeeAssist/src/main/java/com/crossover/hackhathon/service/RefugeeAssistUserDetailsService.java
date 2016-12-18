package com.crossover.hackhathon.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crossover.hackhathon.config.security.RefugeeAssistUserDetails;
import com.crossover.hackhathon.model.RefUser;
import com.crossover.hackhathon.repository.RefUserRepository;


/**
 * 
 * @author murathacioglu.
 */
@Service
@Transactional(value = "masterTransactionManager", readOnly = true)
public class RefugeeAssistUserDetailsService implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(RefugeeAssistUserDetailsService.class);
	

	@Autowired
	private RefUserRepository userRepository;
	

	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		RefUser petraUser;
		petraUser = userRepository.findByUsername(username);
		
		if (petraUser == null) {
            logger.info("USER NOT PRESENT for {}.", username);
			throw new UsernameNotFoundException("Username \"" + username + "\" not found!");
		}
		
		if(petraUser instanceof RefUser) {
			return new RefugeeAssistUserDetails(petraUser, AuthorityUtils.createAuthorityList(petraUser.getUserRole()));
		}
		return new RefugeeAssistUserDetails(petraUser);		
	}

}

