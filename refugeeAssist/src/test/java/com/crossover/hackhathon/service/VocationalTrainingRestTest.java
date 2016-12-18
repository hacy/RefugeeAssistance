package com.crossover.hackhathon.service;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import com.crossover.hackhathon.RefugeeAssistApplication;
import com.crossover.hackhathon.RefugeeAssistConstants;
import com.crossover.hackhathon.controller.vocationaltraining.VocationalTrainingTransport;
import com.jayway.jsonpath.JsonPath;


@SuppressWarnings("deprecation")
@WebMvcTest(RefugeeAssistApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RefugeeAssistApplication.class)
@WebAppConfiguration
public class VocationalTrainingRestTest {
	
	private MockMvc mockMvc;
    @SuppressWarnings("rawtypes")
	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private Integer idVal;
    
    @Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}
	
    @Test
    public void listVocationalTraining() throws Exception {
    			mockMvc.perform(get(RefugeeAssistConstants.BASE_URL+"voct/"))
    			.andExpect(status().isOk())
    			.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.pageSize", is(25)));
    }
    
    @Test
    public void getVocationalTraining() throws Exception {
    			this.setVocationalTrainingSuccess();
    			mockMvc.perform(get(RefugeeAssistConstants.BASE_URL+"voct/"+idVal.toString()))
    			.andExpect(status().isOk())
    			.andExpect(content().contentType("application/json;charset=UTF-8"));
	}
    
    @Test
    public void setVocationalTrainingSuccess() throws Exception{
    	VocationalTrainingTransport vt = new VocationalTrainingTransport();
    	vt.setAddressId(1L);
    	vt.setCost(0.0);
    	vt.setName("IT Course");
    	vt.setWebsite("www.itcourse.com");
    	MvcResult result =mockMvc.perform(post(RefugeeAssistConstants.BASE_URL+"voct/")
    	.content(this.json(vt))
		.contentType(RefugeeAssistConstants.contentType))
    	.andExpect(status().isOk())
    	.andExpect(content().contentTypeCompatibleWith(RefugeeAssistConstants.contentType))
    	.andReturn();
        String content = result.getResponse().getContentAsString();
        idVal = JsonPath.read(content, "$.id");
    }
   
    
    @Test
    public void deleteVocationalTraining() throws Exception{
		this.setVocationalTrainingSuccess();
		mockMvc.perform(delete(RefugeeAssistConstants.BASE_URL+"voct/"+idVal.toString()))
		.andExpect(status().isOk());

    }
    
	
    
    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
            .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
            .findAny()
            .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }
    
    @SuppressWarnings("unchecked")
	protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

}
