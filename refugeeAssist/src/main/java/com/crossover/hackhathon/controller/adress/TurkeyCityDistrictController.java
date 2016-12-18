package com.crossover.hackhathon.controller.adress;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crossover.hackhathon.RefugeeAssistApplication;
import com.crossover.hackhathon.exception.WebApplicationException;
import com.crossover.hackhathon.service.TurkeyCityDistrictService;


@RestController
@RequestMapping(value = RefugeeAssistApplication.API_TENANT_PATH + "/city-district", 
				produces = MediaType.APPLICATION_JSON_VALUE)
public class TurkeyCityDistrictController {

	@Autowired
	private TurkeyCityDistrictService turkeyCityDistrictService;

	
	/**
	 * Implementation of HTTP GET.
	 * 
	 * @param id
	 * @return a city object.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public TurkeyCityDistrictTransport get(@PathVariable("id") Long id) {
		return turkeyCityDistrictService.getTurkeyCityDistrictTransport(id);
	}
	
	/**
	 * Implementation of HTTP GET.
	 * 
	 * @param pageNumber
	 * @return a list of city objects.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<TurkeyCityDistrictTransport> listTurkeyCityDistricts(
			@RequestParam(required = false) Long cityId) {
		return turkeyCityDistrictService.listTurkeyCityDistricts(cityId);
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
	public TurkeyCityDistrictTransport create(@RequestBody TurkeyCityDistrictTransport cityTransport) {
		return turkeyCityDistrictService.createTurkeyCityDistrict(cityTransport);
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
	public TurkeyCityDistrictTransport update(@PathVariable("id") Long id, 
			@RequestBody TurkeyCityDistrictTransport cityTransport) {
		return turkeyCityDistrictService.updateTurkeyCityDistrict(id, cityTransport);
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

