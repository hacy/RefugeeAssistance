package com.crossover.hackhathon.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crossover.hackhathon.controller.adress.TurkeyCityDistrictTransport;
import com.crossover.hackhathon.model.address.TurkeyCity;
import com.crossover.hackhathon.model.address.TurkeyCityDistrict;
import com.crossover.hackhathon.repository.TurkeyCityDistrictRepository;
import com.crossover.hackhathon.repository.TurkeyCityRepository;
import com.google.common.collect.Lists;

@Service
@Transactional("masterTransactionManager")
public class TurkeyCityDistrictService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	
	@Autowired
	private TurkeyCityRepository turkeyCityRepository;

	@Autowired
	private TurkeyCityDistrictRepository turkeyCityDistrictRepository;

	
	public TurkeyCityDistrict getTurkeyCityDistrict(Long id) {

		TurkeyCityDistrict city = turkeyCityDistrictRepository.findOne(id);
		if(city.equals(null)) {
			throw new ServiceException("City District not found");
		}
		return city;
	}

	public TurkeyCityDistrictTransport getTurkeyCityDistrictTransport(Long id) {

		TurkeyCityDistrict city = turkeyCityDistrictRepository.findOne(id);
		if(city.equals(null)) {
			throw new ServiceException("City not found");
		}
		return new TurkeyCityDistrictTransport(city);
		
	}

	public List<TurkeyCityDistrictTransport> listTurkeyCityDistricts(Long cityId) {
		List<TurkeyCityDistrict> cities;
		if (cityId == null) {
			cities = turkeyCityDistrictRepository.findAllOrderByName();
		} else {
			cities = turkeyCityDistrictRepository.findAllByTurkeyCityIdOrderByName(cityId);
		}
		
		if(cities==null) {
			throw new ServiceException("No City found");
		}
		
		List<TurkeyCityDistrictTransport> listTurkeyCityDistricts = Lists.newArrayList();
		for (TurkeyCityDistrict city : cities) {
			listTurkeyCityDistricts.add(new TurkeyCityDistrictTransport(city));
		}
		return listTurkeyCityDistricts;
	}

	public TurkeyCityDistrictTransport createTurkeyCityDistrict(
			TurkeyCityDistrictTransport turkeyCityDistrictTransport) {
		TurkeyCityDistrict cityDistrict = new TurkeyCityDistrict();
		cityDistrict.setName(turkeyCityDistrictTransport.name);
		cityDistrict.setAsciiName(turkeyCityDistrictTransport.name);
		TurkeyCity turkeyCity = turkeyCityRepository.findOne(turkeyCityDistrictTransport.turkeyCityId);
		cityDistrict.setTurkeyCity(turkeyCity);
		
		try {
			cityDistrict = turkeyCityDistrictRepository.save(cityDistrict);
		} catch (DataIntegrityViolationException e) {
			throw new ServiceException(e.getMessage(), e);
		}	
		
		return new TurkeyCityDistrictTransport(cityDistrict);
	}

	public TurkeyCityDistrictTransport updateTurkeyCityDistrict(Long id,
			TurkeyCityDistrictTransport turkeyCityDistrictTransport) {
		TurkeyCityDistrict cityDistrict = turkeyCityDistrictRepository.findOne(id);
		cityDistrict.setName(turkeyCityDistrictTransport.name);	
		TurkeyCity turkeyCity = turkeyCityRepository.findOne(turkeyCityDistrictTransport.turkeyCityId);
		cityDistrict.setTurkeyCity(turkeyCity);
		
		try {
			cityDistrict = turkeyCityDistrictRepository.save(cityDistrict);
		} catch (DataIntegrityViolationException e) {
			throw new ServiceException(e.getMessage(), e);
		}	
		
		return new TurkeyCityDistrictTransport(cityDistrict);
	}

	public void deleteTurkeyCityDistrict(Long id) {
		
		try {
			turkeyCityRepository.delete(id);	
			logger.info("City District (" + id + ") is deleted");
		} catch (DataIntegrityViolationException e) {
			throw new ServiceException(e.getMessage(), e);
		}	
	}
	
	
}

