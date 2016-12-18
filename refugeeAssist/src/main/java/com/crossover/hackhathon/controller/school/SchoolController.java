package com.crossover.hackhathon.controller.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crossover.hackhathon.RefugeeAssistApplication;
import com.crossover.hackhathon.controller.AController;
import com.crossover.hackhathon.service.IService;
import com.crossover.hackhathon.service.SchoolService;


@RestController
@RequestMapping(value = RefugeeAssistApplication.API_TENANT_PATH + "/school", 
				produces = MediaType.APPLICATION_JSON_VALUE)
public class SchoolController extends AController<SchoolTransport> {
	
	@Autowired
	private SchoolService schoolService;
	
	@Override
	public IService<SchoolTransport> getService() {
		return schoolService;
	}

}
