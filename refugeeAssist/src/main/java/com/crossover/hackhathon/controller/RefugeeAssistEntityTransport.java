package com.crossover.hackhathon.controller;

import java.util.Date;

import com.crossover.hackhathon.model.BaseEntity;

public abstract class RefugeeAssistEntityTransport {

	private Long id;
	private Date createDate;
	private Date updateDate;
	private Double latitude;
	private Double longitude;

	
	public RefugeeAssistEntityTransport() {
		
	}
	
	public RefugeeAssistEntityTransport(BaseEntity petraEntity) {
		this.id = petraEntity.getId();
		this.createDate = petraEntity.getCreateDate();
		this.updateDate = petraEntity.getUpdateDate();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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
