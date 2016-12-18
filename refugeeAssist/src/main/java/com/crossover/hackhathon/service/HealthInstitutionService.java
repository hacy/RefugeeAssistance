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
import com.crossover.hackhathon.controller.healthinstitution.HealthInstitutionTransport;
import com.crossover.hackhathon.model.HealthInstitution;
import com.crossover.hackhathon.repository.HealthInstitutionRepository;
import com.google.common.collect.Lists;

@Service
@Transactional("masterTransactionManager")
public class HealthInstitutionService  implements IService<HealthInstitutionTransport> {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private static String notFoundMessage = "Health Institution not found";

	
	@Autowired
	private HealthInstitutionRepository healthRepository;
	

	@Autowired
	private AddressService addressService;


	public HealthInstitution getHealthInst(Long id) {
		HealthInstitution hi = healthRepository.findOne(id);

		if(hi.equals(null)) {
			throw new ServiceException(notFoundMessage);
		}
		
		return hi;
	}
	

	@Override
	public HealthInstitutionTransport getEntity(Long id) {
		HealthInstitution hi = healthRepository.findOne(id);
		if(hi.equals(null)) {
			throw new ServiceException(notFoundMessage);
		}
		return new HealthInstitutionTransport(hi);
	}

	
	public RefugeeAssistItemListTransport listEntities(Integer pageSize, Integer pageNumber) {
		List<HealthInstitution> healthInstList = healthRepository.findAll();
		List<RefugeeAssistEntityTransport> entityList = Lists.newArrayList();
		for (HealthInstitution vt : healthInstList) {
			entityList.add(new HealthInstitutionTransport(vt));
		}
		RefugeeAssistItemListTransport itemListTransport = new RefugeeAssistItemListTransport();
		itemListTransport.setPageSize(pageSize);
		itemListTransport.setTotalItemCount(healthRepository.count());
		itemListTransport.setItems(entityList);
		return itemListTransport;
	}

	public HealthInstitutionTransport createEntity(HealthInstitutionTransport healthInsTransport) {

		HealthInstitution vt = new HealthInstitution();
		if(healthInsTransport.getAddressId()!=null){
			vt.setAddress(addressService.getAddress(healthInsTransport.getAddressId()));
		}
		vt.setName(healthInsTransport.getName());
		vt.setPhoneNumber(healthInsTransport.getPhoneNumber());
		vt.setWebsite(healthInsTransport.getWebsite());
		vt.setLatitude(healthInsTransport.getLatitude());
		vt.setLongitude(healthInsTransport.getLongitude());
		try {
			vt = healthRepository.save(vt);
		} catch (DataIntegrityViolationException e) {
			throw new ServiceException(e.getMessage(), e);
		}	
		
		return new HealthInstitutionTransport(vt);
	}

	public HealthInstitutionTransport updateEntity(Long id, HealthInstitutionTransport healthInsTransport) {
		HealthInstitution vt = healthRepository.findOne(id);
		
		if(healthInsTransport.getAddressId()!=null){
			vt.setAddress(addressService.getAddress(healthInsTransport.getAddressId()));
		}
		vt.setName(healthInsTransport.getName());
		vt.setPhoneNumber(healthInsTransport.getPhoneNumber());
		vt.setWebsite(healthInsTransport.getWebsite());		
		vt.setLatitude(healthInsTransport.getLatitude());
		vt.setLongitude(healthInsTransport.getLongitude());
		
		try {
			vt = healthRepository.save(vt);
		} catch (DataIntegrityViolationException e) {
			throw new ServiceException(e.getMessage(), e);
		}	
		
		return new HealthInstitutionTransport(vt);
	}
	

	public void deleteEntity(Long id) {
		
		try {
			healthRepository.delete(id);
			logger.info("Health Institution # (" + id + ") is deleted");
		} catch (DataIntegrityViolationException e) {
			throw new ServiceException(e.getMessage(), e);
		}	
	}
	
}

