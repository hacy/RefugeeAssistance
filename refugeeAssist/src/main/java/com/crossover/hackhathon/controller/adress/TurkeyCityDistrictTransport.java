package com.crossover.hackhathon.controller.adress;

import com.crossover.hackhathon.model.address.TurkeyCityDistrict;

/**
 * 
 * @author mhacioglu
 */
public class TurkeyCityDistrictTransport {

	public Long id;
	public String name;
	public Long turkeyCityId;

	
	public TurkeyCityDistrictTransport() {

	}

	public TurkeyCityDistrictTransport(TurkeyCityDistrict turkeyCityDistrict) {
		this.id = turkeyCityDistrict.getId();
		this.name = turkeyCityDistrict.getName();
		this.turkeyCityId = turkeyCityDistrict.getTurkeyCity().getId();
	}

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

	public Long getTurkeyCityId() {
		return turkeyCityId;
	}

	public void setTurkeyCityId(Long turkeyCityId) {
		this.turkeyCityId = turkeyCityId;
	}
	
}

