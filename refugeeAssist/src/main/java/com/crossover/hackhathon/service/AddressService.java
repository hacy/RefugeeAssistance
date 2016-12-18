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
import com.crossover.hackhathon.controller.adress.AddressTransport;
import com.crossover.hackhathon.model.address.Address;
import com.crossover.hackhathon.repository.AddressRepository;
import com.google.common.collect.Lists;

@Service
@Transactional("masterTransactionManager")
public class AddressService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private TurkeyCityService cityService;
	
	@Autowired
	private TurkeyCityDistrictService cityDistrictService;
	

	public Address getAddress(Long id) {
		Address address = addressRepository.findOne(id);
		if(address.equals(null)) {
			throw new ServiceException("Adress not found");
		}
		
		return addressRepository.findOne(id);

	}
	
	public AddressTransport getEntity(Long id) {
		Address address = addressRepository.findOne(id);
		if(address.equals(null)) {
			throw new ServiceException("Adress not found");
		}
		return new AddressTransport(address);
	}

	public RefugeeAssistItemListTransport listEntities(Integer pageSize) {
		List<Address> addresss = addressRepository.findAll();
		List<RefugeeAssistEntityTransport> listAddresss = Lists.newArrayList();
		for (Address category : addresss) {
			listAddresss.add(new AddressTransport(category));
		}
		RefugeeAssistItemListTransport itemListTransport = new RefugeeAssistItemListTransport();
		itemListTransport.setPageSize(pageSize);
		itemListTransport.setTotalItemCount(addressRepository.count());
		itemListTransport.setItems(listAddresss);
		return itemListTransport;
	}

	public AddressTransport createEntity(AddressTransport addressTransport) {
		Address address = new Address();
		address.setAvenue(addressTransport.getAvenue());
		address.setDetail(addressTransport.getDetail());
		address.setRegion(addressTransport.getRegion());
		if (addressTransport.getCityId() != null) {
			address.setCity(cityService.getTurkeyCity(addressTransport.getCityId()));
		}
		if(addressTransport.getDistrictId() != null) {
			address.setDistrict(cityDistrictService.getTurkeyCityDistrict(addressTransport.getDistrictId()));
		}		
		address.setState(addressTransport.getState());
		address.setZipCode(addressTransport.getZipCode());
		address.setCountry(addressTransport.getCountry());
		address.setLatitude(addressTransport.getLatitude());
		address.setLongitude(addressTransport.getLongitude());
		try {
			 return new AddressTransport(addressRepository.save(address));
		} catch (DataIntegrityViolationException e) {
			throw new ServiceException(e.getMessage(), e);
		}		
	}

	public AddressTransport updateEntity(Long id,
			AddressTransport addressTransport) {
		Address address = addressRepository.findOne(id);
		address.setAvenue(addressTransport.getAvenue());
		address.setDetail(addressTransport.getDetail());
		address.setRegion(addressTransport.getRegion());
		if (addressTransport.getCityId() != null) {
			address.setCity(cityService.getTurkeyCity(addressTransport.getCityId()));
		}
		if(addressTransport.getDistrictId() != null) {
			address.setDistrict(cityDistrictService.getTurkeyCityDistrict(addressTransport.getDistrictId()));
		}		
		address.setState(addressTransport.getState());
		address.setZipCode(addressTransport.getZipCode());
		address.setCountry(addressTransport.getCountry());
		address.setLatitude(addressTransport.getLatitude());
		address.setLongitude(addressTransport.getLongitude());
		
		try {
			 return new AddressTransport(addressRepository.save(address));
		} catch (DataIntegrityViolationException e) {
			throw new ServiceException(e.getMessage(), e);
		}		
	}

	public void deleteEntity(Long id) {
		logger.info("Address (" + id + ") is deleted");
		addressRepository.delete(id);
	}
	
}

