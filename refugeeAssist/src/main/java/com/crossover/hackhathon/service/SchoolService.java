package com.crossover.hackhathon.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crossover.hackhathon.controller.RefugeeAssistEntityTransport;
import com.crossover.hackhathon.controller.RefugeeAssistItemListTransport;
import com.crossover.hackhathon.controller.school.SchoolTransport;
import com.crossover.hackhathon.model.School;
import com.crossover.hackhathon.repository.SchoolRepository;
import com.google.common.collect.Lists;

@Service
@Transactional("masterTransactionManager")
public class SchoolService implements IService<SchoolTransport> {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private static String notFoundMessage = "School not found";

	@Autowired
	private SchoolRepository schoolRepository;
	
	@Autowired
	private AddressService addressService;
	

	public School getSchool(Long id) {

		School school = schoolRepository.findOne(id);
		if(school==null) {
			throw new ServiceException(notFoundMessage);
		}
		return school;
	}
	
	public SchoolTransport getEntity(Long id) {
		School school = schoolRepository.findOne(id);
		if(school==null) {
			throw new ServiceException(notFoundMessage);
		}
		return new SchoolTransport(school);
	}

	public RefugeeAssistItemListTransport listEntities(Integer pageSize, Integer pageNumber) {
		List<School> schoolList = schoolRepository.findAll();
		if(schoolList==null) {
			throw new ServiceException(notFoundMessage);
		}
		
		List<RefugeeAssistEntityTransport> listSchoolEntity = Lists.newArrayList();
		for (School school : schoolList) {
			listSchoolEntity.add(new SchoolTransport(school));
		}
		
		RefugeeAssistItemListTransport itemListTransport = new RefugeeAssistItemListTransport();
		itemListTransport.setPageSize(pageSize);
		itemListTransport.setTotalItemCount(schoolRepository.count());
		itemListTransport.setItems(listSchoolEntity);
		return itemListTransport;
	}

	public SchoolTransport createEntity(SchoolTransport schoolTransport) {
		
		School school = new School();
		
		if(schoolTransport.getAddressId()!=null){
			school.setAddress(addressService.getAddress(schoolTransport.getAddressId()));
		}
		school.setEmail(schoolTransport.getEmail());
		school.setName(schoolTransport.getName());
		school.setPhoneNumber(schoolTransport.getPhoneNumber());
		school.setType(schoolTransport.getType());
		school.setWebsite(schoolTransport.getWebsite());
		school.setLatitude(schoolTransport.getLatitude());
		school.setLongitude(schoolTransport.getLongitude());
		try {
			school = schoolRepository.save(school);
		} catch (DataIntegrityViolationException e) {
			throw new ServiceException(e.getMessage(), e);
		}	
		
		return new SchoolTransport(school);
	}

	public SchoolTransport updateEntity(Long id, SchoolTransport schoolTransport) {
		School school = schoolRepository.findOne(id);
		if(schoolTransport.getAddressId()!=null){
			school.setAddress(addressService.getAddress(schoolTransport.getAddressId()));
		}
		school.setEmail(schoolTransport.getEmail());
		school.setName(schoolTransport.getName());
		school.setPhoneNumber(schoolTransport.getPhoneNumber());
		school.setType(schoolTransport.getType());
		school.setWebsite(schoolTransport.getWebsite());
		school.setLatitude(schoolTransport.getLatitude());
		school.setLongitude(schoolTransport.getLongitude());
		try {
			school = schoolRepository.save(school);
		} catch (DataIntegrityViolationException e) {
			throw new ServiceException(e.getMessage(), e);
		}	
				
		return new SchoolTransport(school);
	}

	public void deleteEntity(Long id) {
		try {
			schoolRepository.delete(id);
			logger.info("School (" + id + ") is deleted");
		} catch (DataIntegrityViolationException e) {
			throw new ServiceException(e.getMessage(), e);
		}	
	}
	
}

