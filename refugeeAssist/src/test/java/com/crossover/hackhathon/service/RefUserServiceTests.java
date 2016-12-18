package com.crossover.hackhathon.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.crossover.hackhathon.RefugeeAssistApplication;
import com.crossover.hackhathon.controller.refugee.RefUserTransport;
import com.crossover.hackhathon.exception.WebApplicationException;
import com.crossover.hackhathon.model.RefUser;
import com.crossover.hackhathon.model.Role;


@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@EnableAutoConfiguration
@SpringApplicationConfiguration(classes = RefugeeAssistApplication.class)
@WebAppConfiguration
public class RefUserServiceTests {

	@Autowired
	private RefUserService userService;
	
	@Test
	public void createUserSuccess(){
		RefUser user = new RefUser();
		user.setFirstName("Murat");
		user.setLastName("Hacioglu");
		user.setUsername("d1manager@refugee.com");
		user.setPassword("d1manager");
		user.setNationality("Turkish");
		user.setUserRole(Role.GOVERNMENT_MEMBER.toString());
		user.setLatitude(41.0082);
		user.setLongitude(28.9784);
		RefUserTransport userTransport = new RefUserTransport(user);
		userTransport = userService.createRefugeeUser(userTransport);
		assertNotNull(userTransport.getId());
	}
	
	@Test(expected = ServiceException.class)
	public void createUserFailDuplicate() {
		RefUser user = new RefUser();
		user.setFirstName("Murat");
		user.setLastName("Hacioglu");
		user.setUsername("d1manager@refugee.com");
		user.setPassword("d1manager");
		user.setUserRole(Role.REFUGEE.toString());
		user.setNationality("Turkish");
		user.setLatitude(41.0082);
		user.setLongitude(28.9784);
		RefUserTransport userTransport = new RefUserTransport(user);
		userTransport = userService.createRefugeeUser(userTransport);
	}
	
	@Test(expected = ServiceException.class)
	public void createUserFail2() {
		RefUser user = new RefUser();
		user.setUsername("d2manager@refugee.com");
		user.setPassword("d2manager");
		user.setLatitude(41.0092);
		user.setLongitude(28.9684);
		RefUserTransport userTransport = new RefUserTransport(user);
		userTransport = userService.createRefugeeUser(userTransport);
	}
	
	@Test
	public void getUserById(){
		RefUser user= userService.getUser(1L);
		assertNotNull(user.getId());
	}
	
	@Test(expected = WebApplicationException.class)
	public void getUserByUsername(){
		RefUserTransport rt= userService.getRefUserByUsername("d1manager@refugee.com");
		assertNotNull(rt.getId());
	}
	
}
