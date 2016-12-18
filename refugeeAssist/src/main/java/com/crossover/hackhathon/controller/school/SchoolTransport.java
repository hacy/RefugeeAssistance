package com.crossover.hackhathon.controller.school;

import com.crossover.hackhathon.controller.RefugeeAssistEntityTransport;
import com.crossover.hackhathon.model.School;

public class SchoolTransport extends RefugeeAssistEntityTransport {
	
	private String name;
	private String website;
	private Long addressId;
	private String type;
	private String email;
	private String phoneNumber;
	private Double latitude;
	private Double longitude;

	public SchoolTransport(){
		/*
		 * Default construct
		 * */
	}
	
	public SchoolTransport(School school){
		super(school);
		this.setName(school.getName());
		this.website = school.getWebsite();
		if(school.getAddress()!=null){
			this.addressId = school.getAddress().getId();
		}
		this.email = school.getEmail();
		this.phoneNumber = school.getPhoneNumber();
		this.latitude = school.getLatitude();
		this.longitude = school.getLongitude();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
