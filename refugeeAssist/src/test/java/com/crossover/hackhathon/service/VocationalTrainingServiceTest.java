package com.crossover.hackhathon.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.crossover.hackhathon.RefugeeAssistApplication;
import com.crossover.hackhathon.controller.RefugeeAssistItemListTransport;
import com.crossover.hackhathon.controller.adress.AddressTransport;
import com.crossover.hackhathon.controller.refugee.RefUserTransport;
import com.crossover.hackhathon.controller.vocationaltraining.VocationalTrainingTransport;
import com.crossover.hackhathon.model.VocationalTraining;


@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@EnableAutoConfiguration
@SpringApplicationConfiguration(classes = RefugeeAssistApplication.class)
@WebAppConfiguration
public class VocationalTrainingServiceTest {

	@Autowired
	private VocationalTrainingService vtService;
	
	@Autowired
	private AddressService adressService;
	
	@Autowired
	private RefUserService userService;
	

	@Test
	public void setAdressSuccess(){
		AddressTransport adress = new AddressTransport();
		adress.setCityId(1L);
		adress.setCountry("Turkey");
		adress.setDistrictId(1L);
		adress.setDetail("Buyuk camii yanindaki sokak");
		adress.setZipCode("012233");
		adressService.createEntity(adress);
		
	}

	@Test
	public void setVocationalTrainingSuccess(){

		VocationalTraining vt = new VocationalTraining();
		vt.setCost(0.0);
		vt.setLatitude(36.9914);
		vt.setLongitude(35.3308);
		vt.setName("Yuregir ISMEK Vocational Kurs");
		vt.setVocation("IT Expert");
		vt.setPhoneNumber("0212 333 22 33");
		vt.setWebsite("www.yuregirismek.gov.tr");
		vt.setAddress(adressService.getAddress(1L));
		
		VocationalTrainingTransport vt2 = new VocationalTrainingTransport(vt);
		vtService.createEntity(vt2);
	}
	
	@Test
	public void listVocationalTraining() {
		RefugeeAssistItemListTransport vocationalTrainingCenterList =vtService.listEntities(0, 25);
		assertNotNull(vocationalTrainingCenterList);
		assertNotNull(vocationalTrainingCenterList.getItems().get(0).getId());
		vocationalTrainingCenterList.getItems().stream().forEach(j -> assertNotNull(j.getCreateDate()));
	}
	
	@Test
	public void getVocationalTraining() {
		RefugeeAssistItemListTransport vocationalTrainingCenterList =vtService.listEntities(0, 25);
		assertNotNull(vocationalTrainingCenterList.getItems().get(0).getId());
		assertNotNull(vtService.getEntity(vocationalTrainingCenterList.getItems().get(0).getId()));
	}
	
	@Test
	public void deleteVt(){
		RefugeeAssistItemListTransport vocationalTrainingCenterList =vtService.listEntities(0, 25);
		int firstCount = vocationalTrainingCenterList.getItems().size();
		vtService.deleteEntity(vocationalTrainingCenterList.getItems().get(0).getId());
		int secondCount = vtService.listEntities(0, 25).getItems().size();
		assertEquals(firstCount-1, secondCount);

	}
	
	protected RefUserTransport getUser(String username) {
		RefUserTransport user = userService.getRefUserByUsername(username);
		if (user==null) {
			fail("user doesn't exist");
		}
		return user;
	}

}
