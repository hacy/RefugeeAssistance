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
import com.crossover.hackhathon.controller.school.SchoolTransport;
import com.crossover.hackhathon.model.School;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@EnableAutoConfiguration
@SpringApplicationConfiguration(classes = RefugeeAssistApplication.class)
@WebAppConfiguration
public class SchoolServiceTest {
	
	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private AddressService adressService;
	
	
	@Test
	public void setSchoolSuccess(){

		School school = new School();
		school.setLatitude(36.9914);
		school.setLongitude(35.3308);
		school.setName("Istanbul technical university");
		school.setPhoneNumber("0212 333 22 33");
		school.setWebsite("www.itu.gov.tr");
		school.setAddress(adressService.getAddress(1L));
		
		SchoolTransport vt2 = new SchoolTransport(school);
		schoolService.createEntity(vt2);
	}
	

	@Test
	public void getSchool() {
		RefugeeAssistItemListTransport schoolList =schoolService.listEntities(0, 25);
		assertNotNull(schoolList.getItems().get(0).getId());
		assertNotNull(schoolService.getEntity(schoolList.getItems().get(0).getId()));
	}

	@Test
	public void listSchool() {
		RefugeeAssistItemListTransport schoolList =schoolService.listEntities(0, 25);
		assertNotNull(schoolList);
		assertNotNull(schoolList.getItems().get(0).getId());
		schoolList.getItems().stream().forEach(j -> assertNotNull(j.getCreateDate()));
	}
	
	@Test
	public void deleteSchool(){
		RefugeeAssistItemListTransport sList =schoolService.listEntities(0, 25);
		assertNotNull(sList);
		int firstCount = sList.getItems().size();
		if(firstCount==0){
			setSchoolSuccess();
		}
		schoolService.deleteEntity(sList.getItems().get(0).getId());
		int secondCount = schoolService.listEntities(0, 25).getItems().size();
		assertEquals(firstCount-1, secondCount);

	}
}
