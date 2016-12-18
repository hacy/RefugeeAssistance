package com.crossover.hackhathon.controller.adress;

import java.util.List;

import com.crossover.hackhathon.model.address.TurkeyCity;
import com.crossover.hackhathon.model.address.TurkeyCityDistrict;
import com.google.common.collect.Lists;

/**
 * 
 * @author mhacioglu
 */
public class TurkeyCityTransport {

	public Long id;
	public String name;
	public List<Long> turkeyCityDistrictIds;

	
	public TurkeyCityTransport() {

	}

	public TurkeyCityTransport(TurkeyCity turkeyCity) {
		this.id = turkeyCity.getId();
		this.name = turkeyCity.getName();
		this.turkeyCityDistrictIds = Lists.newLinkedList();
		for (TurkeyCityDistrict turkeyCityDistrict : turkeyCity.getTurkeyCityDistricts()) {
			this.turkeyCityDistrictIds.add(turkeyCityDistrict.getId());
		}
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

	public List<Long> getTurkeyCityDistrictIds() {
		return turkeyCityDistrictIds;
	}

	public void setTurkeyCityDistrictIds(List<Long> turkeyCityDistrictIds) {
		this.turkeyCityDistrictIds = turkeyCityDistrictIds;
	}

}

