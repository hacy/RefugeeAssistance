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
import com.crossover.hackhathon.controller.vocationaltraining.VocationalTrainingTransport;
import com.crossover.hackhathon.model.VocationalTraining;
import com.crossover.hackhathon.repository.VocationalTrainingRepository;
import com.google.common.collect.Lists;

@Service
@Transactional("masterTransactionManager")
public class VocationalTrainingService  implements IService<VocationalTrainingTransport> {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private static String notFoundMessage = "Vocational Training Center not found";
	
	@Autowired
	private VocationalTrainingRepository vtRepository;
	
	@Autowired
	private AddressService addressService;
	
	

	public VocationalTraining getVT(Long id) {
		VocationalTraining vt = vtRepository.findOne(id);
		if(vt==null) {
			throw new ServiceException(notFoundMessage);
		}
		return vt;
	}


	@Override
	public VocationalTrainingTransport getEntity(Long id) {
		VocationalTraining vt = vtRepository.findOne(id);
		if(vt==null) {
			throw new ServiceException(notFoundMessage);
		}
		return new VocationalTrainingTransport(vt);
	}
	

	public RefugeeAssistItemListTransport listEntities(Integer pageSize, Integer pageNumber) {
		List<VocationalTraining> vtList = vtRepository.findAll();
		if(vtList==null) {
			throw new ServiceException(notFoundMessage);
		}
		
		List<RefugeeAssistEntityTransport> entityList = Lists.newArrayList();
		for (VocationalTraining vt : vtList) {
			entityList.add(new VocationalTrainingTransport(vt));
		}
		RefugeeAssistItemListTransport itemListTransport = new RefugeeAssistItemListTransport();
		itemListTransport.setPageSize(pageSize);
		itemListTransport.setTotalItemCount(vtRepository.count());
		itemListTransport.setItems(entityList);
		return itemListTransport;
	}


	public void deleteEntity(Long id) {
		
		try {
			vtRepository.delete(id);
			logger.info("Vocational Training Center: # (" + id + ") is deleted");
		} catch (DataIntegrityViolationException e) {
			throw new ServiceException(e.getMessage(), e);
		}	
	}
	
	@Override
	public VocationalTrainingTransport createEntity(VocationalTrainingTransport vtTransport) {

		VocationalTraining vt = new VocationalTraining();
		if(vtTransport.getAddressId()!=null){
			vt.setAddress(addressService.getAddress(vtTransport.getAddressId()));
		}
		vt.setName(vtTransport.getName());
		vt.setPhoneNumber(vtTransport.getPhoneNumber());
		vt.setCost(vtTransport.getCost());
		vt.setWebsite(vtTransport.getWebsite());
		vt.setVocation(vtTransport.getVocation());
		try {
			vt = vtRepository.save(vt);
		} catch (DataIntegrityViolationException e) {
			throw new ServiceException(e.getMessage(), e);
		}	
		
		return new VocationalTrainingTransport(vt);
	}



	@Override
	public VocationalTrainingTransport updateEntity(Long id, VocationalTrainingTransport vtTransport) {

		VocationalTraining vt = vtRepository.findOne(id);
		if(vt.equals(null)) {
			throw new ServiceException(notFoundMessage);
		}
		
		if(vtTransport.getAddressId()!=null){
			vt.setAddress(addressService.getAddress(vtTransport.getAddressId()));
		}
		vt.setName(vtTransport.getName());
		vt.setPhoneNumber(vtTransport.getPhoneNumber());
		vt.setCost(vtTransport.getCost());
		vt.setWebsite(vtTransport.getWebsite());
		vt.setVocation(vtTransport.getVocation());
		try {
			vt = vtRepository.save(vt);
		} catch (DataIntegrityViolationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
		
		return new VocationalTrainingTransport(vt);
	}
}

