package com.crossover.hackhathon.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.crossover.hackhathon.exception.WebApplicationException;
import com.crossover.hackhathon.service.IService;

public abstract class AController<T> {

	public abstract IService<T> getService();
	
	/**
	 * Implementation of HTTP GET.
	 * 
	 * @param id
	 * @return an entity object.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public T get(@PathVariable("id") Long id) {
		if(getService().getEntity(id)!=null){
			return getService().getEntity(id);
		}
		throw new WebApplicationException(HttpStatus.NO_CONTENT);
	}
	
	/**
	 * Implementation of HTTP GET.
	 * 
	 * @param pageNumber
	 * @return a list of entity objects.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public RefugeeAssistItemListTransport list(
			@RequestParam(required = false, defaultValue = "25") Integer pageSize,
			@RequestParam(required = false, defaultValue = "0") Integer pageNumber) {
		RefugeeAssistItemListTransport list = getService().listEntities(pageSize, pageNumber);
		if(list.getPageSize()!=0){
			return getService().listEntities(pageSize, pageNumber);
		}
		throw new WebApplicationException(HttpStatus.NO_CONTENT);
	}

	/**
	 * Implementation of HTTP POST.
	 * 
	 * Creates an entity object.
	 *
	 * @param entity transport
	 * @return new entity object.
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public T create(@RequestBody T entityTransport) {
		return getService().createEntity(entityTransport);
	}

	/**
	 * Implementation of HTTP PUT.
	 * 
	 * Updates an entity object.
	 * 
	 * @param id
	 * @param entityTransport
	 * @return updated entity object.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public T update(@PathVariable("id") Long id, @RequestBody T entityTransport) {
		return getService().updateEntity(id, entityTransport);
	}

	/**
	 * Implementation of HTTP DELETE.
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id) {
		getService().deleteEntity(id);
	}
	
}
