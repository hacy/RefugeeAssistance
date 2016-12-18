package com.crossover.hackhathon.model;

import java.util.Date;

public interface BaseEntity {

	public Long getId();
	
	public void setId(Long id);
	
	public Date getCreateDate();

	public void setCreateDate(Date createDate);
	
	public Date getUpdateDate();

	public void setUpdateDate(Date updateDate);
	
	public Double getLatitude();
	
	public void setLatitude(Double latitude);

	public Double getLongitude();
	
	public void setLongitude(Double longitude);

	
	
}
