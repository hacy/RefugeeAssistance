package com.crossover.hackhathon.model.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.crossover.hackhathon.model.ADateable;

@Entity
@Table(name = "address")
public class Address extends ADateable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** Cadde adi */
	@Column
	private String avenue;

	/** Detayli acik adres */
	@Column
	private String detail;

	/** Bolge */
	@Column
	private TurkeyRegion region;
	
	@Column
	private double latitude;
	
	@Column
	private double longitude;
	
	
	@JoinColumn(name = "city_id")
	@ManyToOne(fetch = FetchType.EAGER)
	private TurkeyCity city;

	@JoinColumn(name = "district_id")
	@ManyToOne(fetch = FetchType.EAGER)
	private TurkeyCityDistrict district;

	@Column
	private String state;

	@Column
	private String zipCode;
	
	@Column
	private String country;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public TurkeyCity getCity() {
		return city;
	}

	public void setCity(TurkeyCity city) {
		this.city = city;
	}

	public TurkeyCityDistrict getDistrict() {
		return district;
	}

	public void setDistrict(TurkeyCityDistrict district) {
		this.district = district;
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
			
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

}

