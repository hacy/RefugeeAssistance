package com.crossover.hackhathon.model.address;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * il domain sinifi.
 * 
 * @author Petra team.
 */
@Entity
@Table(name = "turkey_city")
public class TurkeyCity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(min = 3, max = 50)
	private String name;
	
	@NotNull
	@Size(min = 3, max = 50)
	private String asciiName;
	
    @OneToMany(fetch = FetchType.LAZY, mappedBy="turkeyCity")
    private List<TurkeyCityDistrict> turkeyCityDistricts;

	
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

	public List<TurkeyCityDistrict> getTurkeyCityDistricts() {
		return turkeyCityDistricts;
	}

	public void setTurkeyCityDistricts(List<TurkeyCityDistrict> turkeyCityDistricts) {
		this.turkeyCityDistricts = turkeyCityDistricts;
	}
	
}

