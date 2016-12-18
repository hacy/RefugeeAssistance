package com.crossover.hackhathon.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.crossover.hackhathon.RefugeeAssistApplication;
import com.crossover.hackhathon.controller.adress.AddressTransport;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@EnableAutoConfiguration
@SpringApplicationConfiguration(classes = RefugeeAssistApplication.class)
@WebAppConfiguration
public class AddressServiceTest {

	
	@Autowired
	private AddressService adressService;
	
	@Test
	public void setAdressSuccess(){
		AddressTransport adress = new AddressTransport();
		adress.setCityId(1L);
		adress.setCountry("Turkey");
		adress.setDistrictId(12L);
		adress.setLatitude(99.33);
		adress.setLongitude(43.90);
		adress.setDetail("Details about adress");
		adress.setZipCode("012233");
		adressService.createEntity(adress);
		
	}
}
