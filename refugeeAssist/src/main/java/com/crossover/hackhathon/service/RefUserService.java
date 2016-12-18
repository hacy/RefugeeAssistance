package com.crossover.hackhathon.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crossover.hackhathon.RefugeeUtils;
import com.crossover.hackhathon.config.security.RefUserContextService;
import com.crossover.hackhathon.controller.refugee.RefUserTransport;
import com.crossover.hackhathon.exception.WebApplicationException;
import com.crossover.hackhathon.model.RefUser;
import com.crossover.hackhathon.repository.RefUserRepository;

@Service
@Transactional("masterTransactionManager")
public class RefUserService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RefUserContextService refUserContextService;
	
	@Autowired
	private RefUserRepository userRepository;

	
	public RefUserTransport getCurrentUser() {
		RefUser refUser = refUserContextService.getCurrentUser();
        return createTransport(refUser);
    }
	
	
	public RefUserTransport getRefUserByUsername(String userName) {		
		RefUser petraUser = refUserContextService.getUserByName(userName);
        if (petraUser == null) {
        	throw new WebApplicationException(HttpStatus.NOT_FOUND);
        }
		RefUser currentUser = refUserContextService.getCurrentUser();
		if (!currentUser.equals(petraUser)) {
			logger.info("Access to the user " + userName
					+ " is forbidden for the user " + currentUser.getId());
			throw new WebApplicationException(HttpStatus.FORBIDDEN);
		}
		return createTransport(petraUser);
	}


	public RefUser getUser(Long id) {
		RefUser refUser = userRepository.findOne(id);			
        if (refUser == null) {
        	throw new WebApplicationException(HttpStatus.NOT_FOUND);
        }
		return refUser;
	}
	
	private void checkDuplicate(RefUserTransport petraUserTransport){
		RefUser rUser = userRepository.findByUsername(petraUserTransport.getUserName());
		if(rUser!=null){
			throw new ServiceException("Duplicate user");
		}
	}
	
	public RefUserTransport createRefugeeUser(RefUserTransport petraUserTransport) {
		
		this.checkDuplicate(petraUserTransport);
		
		if(!RefugeeUtils.isValidEmailAddress(petraUserTransport.getUserName())) {
			throw new WebApplicationException(HttpStatus.BAD_REQUEST, "Your username must be valid email address.");
		}	
		
		RefUser user = new RefUser();
		petraUserTransport.setEnabled(true);
		petraUserTransport.updatePetraUser(user);		
		user.setPassword(passwordEncoder.encode(petraUserTransport.getPassword()));
		user.setLatitude(petraUserTransport.getLatitude());
		user.setLongitude(petraUserTransport.getLongitude());
		if(petraUserTransport.getFirstName()==null||petraUserTransport.getLastName()==null){
			throw new ServiceException("First name and last name must be entered");
		}
		if(petraUserTransport.getNationality()==null){
			throw new ServiceException("Nationality must be entered");
		}
		
		user.setFirstName(petraUserTransport.getFirstName());
		user.setLastName(petraUserTransport.getLastName());
		user.setGender(petraUserTransport.getGender());
		user.setLanguage(petraUserTransport.getLanguage());
		user.setNationality(petraUserTransport.getNationality());
		user.setUserRole(petraUserTransport.getUserRole());
		try {
			user = userRepository.save(user);
		} catch (DataIntegrityViolationException e) {
			throw new ServiceException(e.getMessage(), e);
		}	
		
		return createTransport(user);	
	}
			

	private RefUserTransport createTransport(RefUser refUser) {
		RefUserTransport petraMasterUserTransport = new RefUserTransport();		
		petraMasterUserTransport.loadPetraUser(refUser);		
		return petraMasterUserTransport;
	}

	public void deletePetraUser(Long id) {
		try {
			userRepository.delete(id);	
		} catch (DataIntegrityViolationException e) {
			throw new ServiceException(e.getMessage(), e);
		}	
		
	}

}
