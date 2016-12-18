package com.crossover.hackhathon.controller.healthinstitution;

import com.crossover.hackhathon.controller.RefugeeAssistEntityTransport;
import com.crossover.hackhathon.model.HealthInstitution;

public class HealthInstitutionTransport  extends RefugeeAssistEntityTransport {
	
	private String name;
	private String website;
	private Long addressId;
	private String type;
	private String email;
	private String phoneNumber;
	private Double latitude;
	private Double longitude;
	
	public HealthInstitutionTransport(){
		/*
		 * default construct
		 * */
	}
	
	public HealthInstitutionTransport(HealthInstitution healthIns){
		super(healthIns);
		this.name = healthIns.getName();
		this.website = healthIns.getWebsite();
		if(healthIns.getAddress()!=null){
			this.addressId = healthIns.getAddress().getId();
		}
		this.email = healthIns.getEmail();
		this.phoneNumber = healthIns.getPhoneNumber();
		this.latitude = healthIns.getLatitude();
		this.longitude = healthIns.getLongitude();
	}

	public String getName() {
		return name;
	}

	public String getWebsite() {
		return website;
	}

	public Long getAddressId() {
		return addressId;
	}

	public String getType() {
		return type;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

}
