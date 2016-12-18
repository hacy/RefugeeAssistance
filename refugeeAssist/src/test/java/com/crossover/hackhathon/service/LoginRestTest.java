package com.crossover.hackhathon.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.util.Arrays;

import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.GreaterThan;
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
import com.crossover.hackhathon.controller.LoginTransport;
import com.jayway.jsonpath.JsonPath;

@SuppressWarnings("deprecation")
@WebMvcTest(RefugeeAssistApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RefugeeAssistApplication.class)
@WebAppConfiguration
public class LoginRestTest {

	private MockMvc mockMvc;
    @SuppressWarnings("rawtypes")
	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}
	 
    @Test
    public void testLoginFail() throws Exception{
    	LoginTransport logint = new LoginTransport();
    	logint.setUsername("d1manager@refugee.com");
    	logint.setPassword("wrongpass");
    	MvcResult result= mockMvc.perform(post(RefugeeAssistConstants.BASE_URL+"login/")
    	.content(this.json(logint))
		.contentType(RefugeeAssistConstants.contentType))
    	.andReturn();
        String content = result.getResponse().getContentAsString();
        assertThat(content.isEmpty(), is(true));

        System.out.println(content);

    }
	
    @Test
    public void testLoginSuccess() throws Exception{
    	LoginTransport logint = new LoginTransport();
    	logint.setUsername("d1manager@refugee.com");
    	logint.setPassword("d1manager");
    	MvcResult result =mockMvc.perform(post(RefugeeAssistConstants.BASE_URL+"login/")
    	.content(this.json(logint))
		.contentType(RefugeeAssistConstants.contentType))
    	.andExpect(status().isOk())
    	.andExpect(content().contentTypeCompatibleWith(RefugeeAssistConstants.contentType))
    	.andReturn();
        String content = result.getResponse().getContentAsString();
        String ssoToken = JsonPath.read(content, "$.token");
        assertThat(ssoToken.length(), new GreaterThan<>(3));

        System.out.println(ssoToken);
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
