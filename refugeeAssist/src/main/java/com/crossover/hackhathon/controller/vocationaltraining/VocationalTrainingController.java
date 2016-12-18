package com.crossover.hackhathon.controller.vocationaltraining;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crossover.hackhathon.RefugeeAssistApplication;
import com.crossover.hackhathon.controller.AController;
import com.crossover.hackhathon.service.IService;
import com.crossover.hackhathon.service.VocationalTrainingService;

@RestController
@RequestMapping(value = RefugeeAssistApplication.API_TENANT_PATH + "/voct", 
				produces = MediaType.APPLICATION_JSON_VALUE)
public class VocationalTrainingController extends AController<VocationalTrainingTransport> {

	@Autowired
	private VocationalTrainingService vtService;
	
	@Override
	public IService<VocationalTrainingTransport> getService() {
		return vtService;
	}

}
