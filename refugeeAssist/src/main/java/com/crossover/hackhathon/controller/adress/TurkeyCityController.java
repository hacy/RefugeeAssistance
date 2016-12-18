package com.crossover.hackhathon.controller.adress;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crossover.hackhathon.RefugeeAssistApplication;
import com.crossover.hackhathon.exception.WebApplicationException;
import com.crossover.hackhathon.service.TurkeyCityService;


@RestController
@RequestMapping(value = RefugeeAssistApplication.API_TENANT_PATH + "/city", 
				produces = MediaType.APPLICATION_JSON_VALUE)
public class TurkeyCityController {

	@Autowired
	private TurkeyCityService turkeyCityService;

	
	/**
	 * Implementation of HTTP GET.
	 * 
	 * @param id
	 * @return a city object.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public TurkeyCityTransport get(@PathVariable("id") Long id) {
		return turkeyCityService.getTurkeyCityTransport(id);
	}
	
	/**
	 * Implementation of HTTP GET.
	 * 
	 * @param pageNumber
	 * @return a list of city objects.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<TurkeyCityTransport> listTurkeyCitys() {
		return turkeyCityService.listTurkeyCitys();
	}

	/**
	 * Implementation of HTTP POST.
	 * 
	 * Creates a city object.
	 *
	 * @param cityTransport
	 * @return new city object.
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public TurkeyCityTransport create(@RequestBody TurkeyCityTransport cityTransport) {
		return turkeyCityService.createTurkeyCity(cityTransport);
	}

	/**
	 * Implementation of HTTP PUT.
	 * 
	 * Updates a city object.
	 * 
	 * @param id
	 * @param cityTransport
	 * @return updated city object.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public TurkeyCityTransport update(@PathVariable("id") Long id, 
			@RequestBody TurkeyCityTransport cityTransport) {
		return turkeyCityService.updateTurkeyCity(id, cityTransport);
	}
	
	/**
	 * Implementation of HTTP DELETE.
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id) {
		throw new WebApplicationException(HttpStatus.METHOD_NOT_ALLOWED);
	}	
	
}

