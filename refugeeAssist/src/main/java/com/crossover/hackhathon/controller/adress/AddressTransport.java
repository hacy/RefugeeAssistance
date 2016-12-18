package com.crossover.hackhathon.controller.adress;

import com.crossover.hackhathon.controller.RefugeeAssistEntityTransport;
import com.crossover.hackhathon.model.address.Address;
import com.crossover.hackhathon.model.address.TurkeyRegion;

public class AddressTransport extends RefugeeAssistEntityTransport {

	private String avenue;
	private TurkeyRegion region;
	private Long cityId;
	private Long districtId;
	private String state;
	private String zipCode;
	private String country;
	private String detail;
	private Double latitude;
	private Double longitude;
	
	public AddressTransport() {
		/*
		 * default constructor
		 * */
	}

	
	public AddressTransport(Address address) {
		super(address);
		this.avenue = address.getAvenue();
		this.detail = address.getDetail();
		this.region = address.getRegion();
		if (address.getCity() != null) {
			this.cityId = address.getCity().getId();
		}
		if (address.getDistrict() != null) {
			this.districtId = address.getDistrict().getId();
		}
		this.state = address.getState();
		this.zipCode = address.getZipCode();
		this.country = address.getCountry();
		this.latitude = address.getLatitude();
		this.longitude = address.getLongitude();
	}
	

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	

	public String getAvenue() {
		return avenue;
	}

	public void setAvenue(String avenue) {
		this.avenue = avenue;
	}

	public TurkeyRegion getRegion() {
		return region;
	}

	public void setRegion(TurkeyRegion region) {
		this.region = region;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

