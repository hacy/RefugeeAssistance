package com.crossover.hackhathon.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.crossover.hackhathon.model.address.Address;

@Entity
@Table(name = "vocational_training")
public class VocationalTraining extends ADateable {


	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	@Column
	@NotNull
	private String name;
	
	@Column
	@Size(min = 3, max = 100)
	private String website;

	@Column
	private String phoneNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Address address;

	@Column
	private String vocation;
	
	@Column
	private Double cost;
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
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

	public Double getCost() {
		return cost;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getVocation() {
		return vocation;
	}

	public void setVocation(String vocation) {
		this.vocation = vocation;
	}

	

}
