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

import org.hibernate.validator.constraints.Email;

import com.crossover.hackhathon.model.address.Address;

@Entity
@Table(name = "school")
public class School extends ADateable {

	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	@Column
	@NotNull
	private String name;
	
	@Column
	@Size(min = 3, max = 100)
	private String website;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Address address;
	
	@Column
	private String type;
	
	@Column
	@Email
	private String email;
	
	@Column
	private String phoneNumber;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
