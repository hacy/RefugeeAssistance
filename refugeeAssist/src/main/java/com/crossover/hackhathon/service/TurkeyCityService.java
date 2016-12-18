package com.crossover.hackhathon.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crossover.hackhathon.controller.adress.TurkeyCityTransport;
import com.crossover.hackhathon.model.address.TurkeyCity;
import com.crossover.hackhathon.repository.TurkeyCityRepository;
import com.google.common.collect.Lists;

@Service
@Transactional("masterTransactionManager")
public class TurkeyCityService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private TurkeyCityRepository turkeyCityRepository;
	
	
	public TurkeyCity getTurkeyCity(Long id) {
		
		TurkeyCity city = turkeyCityRepository.findOne(id);
		if(city.equals(null)) {
			throw new ServiceException("City not found");
		}
		logger.info("Turkey city (" + id + ") got");
		return city;
	}

	public TurkeyCityTransport getTurkeyCityTransport(Long id) {
		TurkeyCity city = turkeyCityRepository.findOne(id);
		if(city==null) {
			throw new ServiceException("City not found");
		}
		return new TurkeyCityTransport(city);
	}

	public List<TurkeyCityTransport> listTurkeyCitys() {
		List<TurkeyCity> cities = turkeyCityRepository.findAllOrderByName();
		if(cities==null) {
			throw new ServiceException("No City list found");
		}
		
		List<TurkeyCityTransport> listTurkeyCitys = Lists.newArrayList();
		for (TurkeyCity city : cities) {
			listTurkeyCitys.add(new TurkeyCityTransport(city));
		}
		return listTurkeyCitys;
	}

	public TurkeyCityTransport createTurkeyCity(TurkeyCityTransport turkeyCityTransport) {
		TurkeyCity turkeyCity = new TurkeyCity();
		turkeyCity.setName(turkeyCityTransport.name);
		turkeyCity.setAsciiName(turkeyCityTransport.name);
		try {
			turkeyCity = turkeyCityRepository.save(turkeyCity);
		} catch (DataIntegrityViolationException e) {
			throw new ServiceException(e.getMessage(), e);
		}	
				
		return new TurkeyCityTransport(turkeyCity);
	}

	public TurkeyCityTransport updateTurkeyCity(Long id, TurkeyCityTransport turkeyCityTransport) {
		TurkeyCity turkeyCity = turkeyCityRepository.findOne(id);
		turkeyCity.setName(turkeyCityTransport.name);		
		
		try {
			turkeyCity = turkeyCityRepository.save(turkeyCity);
		} catch (DataIntegrityViolationException e) {
			throw new ServiceException(e.getMessage(), e);
		}	
		
		return new TurkeyCityTransport(turkeyCity);
	}

	public void deleteTurkeyCity(Long id) {
		try {
			turkeyCityRepository.delete(id);	
			logger.info("city (" + id + ") is deleted");
		} catch (DataIntegrityViolationException e) {
			throw new ServiceException(e.getMessage(), e);
		}	
	}
	

}

