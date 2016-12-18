package com.crossover.hackhathon.config.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.crossover.hackhathon.model.RefUser;


/**
 * This is an implementation of Spring's UserDetails interface. 
 * It contains all user's details. 
 * 
 * @author Murat Hacioglu
 */
public class RefugeeAssistUserDetails implements UserDetails {
	
	private static final long serialVersionUID = -5229728800594812484L;

	private RefUser petraUser;
	private List<GrantedAuthority> authorities;
	
	public RefugeeAssistUserDetails(RefUser user) {
		this.petraUser = user;
	}
	
	public RefugeeAssistUserDetails(RefUser user, List<GrantedAuthority> authorities) {
		this.petraUser = user;
		this.authorities = authorities;
	}
	
	public RefUser getPetraUser () {
		return petraUser;
	}
	
	public String getUsername() {
		return petraUser.getUsername();
	}	
	
	@Override
	public String getPassword() {
		return petraUser.getPassword();
	}		
	
	@Override
	public boolean isEnabled() {
		return petraUser.isEnabled();
	}
	
	public boolean isAccountNonExpired() {
		return !petraUser.isAccountExpired();
	}

	public boolean isAccountNonLocked() {
		return !petraUser.isAccountLocked();
	}
	
	public boolean isCredentialsNonExpired() {
		return !petraUser.isCredentialsExpired();
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities() {		
		return this.authorities;
	}

}
