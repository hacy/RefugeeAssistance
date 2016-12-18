
package com.crossover.hackhathon.controller.healthinstitution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crossover.hackhathon.RefugeeAssistApplication;
import com.crossover.hackhathon.controller.AController;
import com.crossover.hackhathon.service.HealthInstitutionService;
import com.crossover.hackhathon.service.IService;

@RestController
@RequestMapping(value = RefugeeAssistApplication.API_TENANT_PATH + "/health", 
				produces = MediaType.APPLICATION_JSON_VALUE)
public class HealthInstitutionController extends AController<HealthInstitutionTransport> {

	@Autowired
	private HealthInstitutionService healthService;
	
	@Override
	public IService<HealthInstitutionTransport> getService() {
		return healthService;
	}

}
