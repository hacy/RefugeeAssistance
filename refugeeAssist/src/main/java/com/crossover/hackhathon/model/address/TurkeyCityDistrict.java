package com.crossover.hackhathon.model.address;

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
 * ilce domain sinifi.
 * 
 * @author Petra team.
 */
@Entity
@Table(name = "turkey_city_district")
public class TurkeyCityDistrict {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(min = 3, max = 50)
	private String name;
	
	@NotNull
	@Size(min = 3, max = 50)
	private String asciiName;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "turkey_city_id")
	private TurkeyCity turkeyCity;

	
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

	public String getAsciiName() {
		return asciiName;
	}

	public void setAsciiName(String asciiName) {
		this.asciiName = asciiName;
	}

	public TurkeyCity getTurkeyCity() {
		return turkeyCity;
	}

	public void setTurkeyCity(TurkeyCity turkeyCity) {
		this.turkeyCity = turkeyCity;
	}
	
}

