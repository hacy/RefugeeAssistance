package com.crossover.hackhathon.model.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Semt domain sinifi.
 * 
 * @author Petra team.
 */
@Entity
@Table(name = "turkey_semt")
public class TurkeySemt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(min = 3, max = 50)
	private String name;
	
	@NotNull
	@Size(min = 3, max = 50)
	@Column(name = "upper_case_name")
	private String upperCaseName;
	
	@NotNull
	@Size(min = 3, max = 50)
	@Column(name = "lower_case_name")
	private String lowerCaseName;
	
	@NotNull
	@Column(name = "zip_code")
	private String zipCode;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "turkey_city_id")
	private TurkeyCity turkeyCity;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "turkey_city_district_id")
	private TurkeyCityDistrict turkeyCityDistrict;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUpperCaseName() {
		return upperCaseName;
	}

	public void setUpperCaseName(String upperCaseName) {
		this.upperCaseName = upperCaseName;
	}

	public String getLowerCaseName() {
		return lowerCaseName;
	}

	public void setLowerCaseName(String lowerCaseName) {
		this.lowerCaseName = lowerCaseName;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public TurkeyCity getTurkeyCity() {
		return turkeyCity;
	}

	public void setTurkeyCity(TurkeyCity turkeyCity) {
		this.turkeyCity = turkeyCity;
	}

	public TurkeyCityDistrict getTurkeyCityDistrict() {
		return turkeyCityDistrict;
	}

	public void setTurkeyCityDistrict(TurkeyCityDistrict turkeyCityDistrict) {
		this.turkeyCityDistrict = turkeyCityDistrict;
	}
	
}

