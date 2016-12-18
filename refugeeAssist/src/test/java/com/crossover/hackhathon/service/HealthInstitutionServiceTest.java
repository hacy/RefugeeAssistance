package com.crossover.hackhathon.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
import com.crossover.hackhathon.controller.healthinstitution.HealthInstitutionTransport;
import com.crossover.hackhathon.model.HealthInstitution;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@EnableAutoConfiguration
@SpringApplicationConfiguration(classes = RefugeeAssistApplication.class)
@WebAppConfiguration
public class HealthInstitutionServiceTest {

	
	@Autowired
	private HealthInstitutionService hiService;
	
	@Autowired
	private AddressService adressService;

	
	@Test
	public void setHiSuccess(){

		HealthInstitution hi = new HealthInstitution();
		hi.setLatitude(36.9914);
		hi.setLongitude(35.3308);
		hi.setName("Medicana Hospital");
		hi.setPhoneNumber("0212 333 22 33");
		hi.setWebsite("www.medicana.com.tr");
		hi.setAddress( adressService.getAddress(setAdressSuccess().getId()));
	
		HealthInstitutionTransport vt2 = new HealthInstitutionTransport(hi);
		hiService.createEntity(vt2);
	}
	

	@Test
	public void getHi() {
		RefugeeAssistItemListTransport hiList =hiService.listEntities(0, 25);
		if(hiList.getItems().size()==0)
			setHiSuccess();
		assertNotNull(hiList.getItems().get(0).getId());
		assertNotNull(hiService.getEntity(hiList.getItems().get(0).getId()));
	}

	@Test
	public void listHi() {
		RefugeeAssistItemListTransport hiList =hiService.listEntities(0, 25);
		if(hiList.getPageSize().intValue()==0)
			this.setHiSuccess();
		assertNotNull(hiList);
		assertNotNull(hiList.getItems().get(0).getId());
		hiList.getItems().stream().forEach(j -> assertNotNull(j.getCreateDate()));
	}
	
	@Test
	public void deleteHi(){
		RefugeeAssistItemListTransport sList =hiService.listEntities(0, 25);
		assertNotNull(sList);
		int firstCount = sList.getItems().size();
		if(firstCount==0){
			setHiSuccess();
		}
		hiService.deleteEntity(sList.getItems().get(0).getId());
		int secondCount = hiService.listEntities(0, 25).getItems().size();
		assertEquals(firstCount-1, secondCount);

	}
	
	
	protected AddressTransport setAdressSuccess(){
		AddressTransport adress = new AddressTransport();
		adress.setCityId(16L);
		adress.setCountry("Turkey");
		adress.setDistrictId(1L);
		adress.setDetail("Near big mall");
		adress.setZipCode("012233");
		return adressService.createEntity(adress);
		
	}
	
}
