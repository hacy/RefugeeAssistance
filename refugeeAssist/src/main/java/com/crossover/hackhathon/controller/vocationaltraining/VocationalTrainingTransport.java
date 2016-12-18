package com.crossover.hackhathon.controller.vocationaltraining;

import com.crossover.hackhathon.controller.RefugeeAssistEntityTransport;
import com.crossover.hackhathon.model.VocationalTraining;

public class VocationalTrainingTransport  extends RefugeeAssistEntityTransport {
	
	private String name;
	private String website;
	private Long addressId;
	private Double cost;
	private String vocation;
	private String phoneNumber;
	private Double latitude;
	private Double longitude;
	

	public  VocationalTrainingTransport(){
		/*
		 * constructor
		 *
		 * */
	}
	
	public VocationalTrainingTransport(VocationalTraining vt){
		super(vt);
		this.name = vt.getName();
		this.website = vt.getWebsite();
		if(vt.getAddress()!=null){
			this.addressId = vt.getAddress().getId();
		}
		this.phoneNumber = vt.getPhoneNumber();
		this.vocation = vt.getVocation();
		this.latitude = vt.getLatitude();
		this.longitude = vt.getLongitude();
		this.cost = vt.getCost();
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

	public Double getCost() {
		return cost;
	}

	public String getVocation() {
		return vocation;
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

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public void setVocation(String vocation) {
		this.vocation = vocation;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

}
