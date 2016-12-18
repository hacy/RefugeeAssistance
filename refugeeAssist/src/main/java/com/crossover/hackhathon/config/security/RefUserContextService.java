package com.crossover.hackhathon.config.security;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.crossover.hackhathon.model.Gender;
import com.crossover.hackhathon.model.RefUser;
import com.crossover.hackhathon.model.Role;
import com.crossover.hackhathon.repository.RefUserRepository;
import com.crossover.hackhathon.service.ServiceException;
import com.google.common.collect.Lists;

/**
 * A service providing access to the spring security session.
 * 
 * This service is also used to manage bootstrapping for the whole application.
 * 
 * @author mhacioglu
 * 
 */
@Component
public class RefUserContextService {
	
	@Autowired
	private RefUserRepository refUserRepository;	

	@Autowired
	private PasswordEncoder passwordEncoder;

    @Value("${security.defaultpassword:admin}")
    private String defaultAdminPassword;
	
	public RefUser getUserByName(String name) {
		return refUserRepository.findByUsername(name);
	}
	
	public List<String> getUsernames() {
		List<String> users = Lists.newArrayList();
		List<RefUser>rList = refUserRepository.findAll();
		
		if(rList==null) {
			throw new ServiceException("No User List has been found");
		}
		for (RefUser user : rList) {
			users.add(user.getUsername());
		}
		
		return users;
	}

	
	@PostConstruct
	public void bootstrapSecuritySettings() {
		// Make sure there is a default ROOT user.
		RefUser sysadminUser = refUserRepository.findByUsername("admin@refugee.com");
        String pw = defaultAdminPassword;
        if (pw == null || pw.length() == 0) {
            pw = "admin";
        }
		if (sysadminUser == null) {
			sysadminUser = new RefUser();
			sysadminUser.setUsername("admin@refugee.com");
			sysadminUser.setPassword(passwordEncoder.encode(pw));
			sysadminUser.setFirstName("System");
			sysadminUser.setLastName("Administrator");
			sysadminUser.setLanguage("tr");
			sysadminUser.setHasLicense(true);
			sysadminUser.setNationality("Syrian");
			sysadminUser.setUserRole(Role.ADMIN.toString());
			sysadminUser.setGender(Gender.WOMAN.toString());
			refUserRepository.save(sysadminUser);
		}
	}
	
	private void checkUserEx(RefUser refUser){
		RefUser ruser =  refUserRepository.findOne(refUser.getId());
		if(ruser==null) {
			throw new ServiceException("Current User not found");
		}
	}

	/**
	 *
	 * @return current authenticated user.
	 */
	public RefUser getCurrentUser() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		if (authentication == null) {
			return null;
		}
		Object principal = authentication.getPrincipal();
		if (principal instanceof String && "anonymousUser".equals((String) principal)) {
			throw new BadCredentialsException("Anonymous access not possible.");
		}
		RefugeeAssistUserDetails userDetails = (RefugeeAssistUserDetails) principal;
		RefUser refUser = userDetails.getPetraUser();
		if (refUser == null || refUser.getId() == null) {
			context.setAuthentication(null);
		}
		else{
			this.checkUserEx(refUser);
			return refUserRepository.findOne(refUser.getId());
		}
		return null;
	}


	public void logout() {
		SecurityContextHolder.getContext().setAuthentication(null);
	}
	
}

