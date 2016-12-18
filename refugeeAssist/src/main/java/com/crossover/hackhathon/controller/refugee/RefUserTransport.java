package com.crossover.hackhathon.controller.refugee;

import java.util.Date;

import com.crossover.hackhathon.controller.RefugeeAssistEntityTransport;
import com.crossover.hackhathon.model.RefUser;


/**
 * Transport class for {@link userRef} class. 
 * 
 * @author Mhacioglu
 */
public class RefUserTransport extends RefugeeAssistEntityTransport {

    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String gender;
    private Date birthDate;
    private String language;
    private String userRole;
    private String nationality;

    private boolean enabled;
    private boolean accountLocked;
    private boolean accountExpired;
    private boolean credentialsExpired;
        
    
	public RefUserTransport() {
		/*
		 * default constructor
		 * */
	}
	
	public RefUserTransport(RefUser userRef) {
		super(userRef);
		this.userName = userRef.getUsername();
		this.firstName = userRef.getFirstName();
		this.lastName = userRef.getLastName();			
		this.gender = userRef.getGender();
		this.birthDate = userRef.getBirthDate();
		this.language = userRef.getLanguage();
		this.password = userRef.getPassword();
		this.enabled = userRef.isEnabled();		
		this.accountLocked = userRef.isAccountLocked();
		this.accountExpired = userRef.isAccountExpired();		
		this.credentialsExpired = userRef.isCredentialsExpired();
		this.setLatitude(userRef.getLatitude());
		this.setLongitude(userRef.getLongitude());
		this.userRole = userRef.getUserRole();
		this.nationality = userRef.getNationality();
	}

	public void updatePetraUser(RefUser userRef) {
		userRef.setId(this.getId());
		userRef.setUsername(this.userName);
		userRef.setFirstName(this.firstName);
		userRef.setLastName(this.lastName);	
		userRef.setGender(this.gender);
		userRef.setBirthDate(this.birthDate);
		userRef.setCreateDate(this.getCreateDate());
		userRef.setUpdateDate(this.getUpdateDate());
		userRef.setLanguage(this.language);
		userRef.setEnabled(this.enabled);		
		userRef.setAccountLocked(this.accountLocked);
		userRef.setAccountExpired(this.accountExpired);		
		userRef.setCredentialsExpired(this.credentialsExpired);
		userRef.setUserRole(this.userRole);
		userRef.setPassword(this.password);
	}

	public void loadPetraUser(RefUser userRef) {
		this.setId(userRef.getId());
		this.userName = userRef.getUsername();
		this.firstName = userRef.getFirstName();
		this.firstName = userRef.getFirstName();
		this.lastName = userRef.getLastName();		
		this.gender = userRef.getGender();
		this.birthDate = userRef.getBirthDate();
		this.setCreateDate(userRef.getCreateDate());	
		this.setUpdateDate(userRef.getUpdateDate());
		this.language = userRef.getLanguage();
		this.enabled = userRef.isEnabled();		
		this.accountLocked = userRef.isAccountLocked();
		this.accountExpired = userRef.isAccountExpired();		
		this.credentialsExpired = userRef.isCredentialsExpired();
		this.userRole = userRef.getUserRole();
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getGender() {
		return gender;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public String getLanguage() {
		return language;
	}

	public String getUserRole() {
		return userRole;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public boolean isAccountLocked() {
		return accountLocked;
	}

	public boolean isAccountExpired() {
		return accountExpired;
	}

	public boolean isCredentialsExpired() {
		return credentialsExpired;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	public void setCredentialsExpired(boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

}

