package com.crossover.hackhathon.service;

import com.crossover.hackhathon.controller.RefugeeAssistItemListTransport;

public interface IService<T> {

	public T getEntity(Long id);

	public RefugeeAssistItemListTransport listEntities(Integer pageSize, Integer pageNumber);

	public T createEntity(T customerTransport);
	
	public T updateEntity(Long id, T customerTransport);
	
	public void deleteEntity(Long id);

}
