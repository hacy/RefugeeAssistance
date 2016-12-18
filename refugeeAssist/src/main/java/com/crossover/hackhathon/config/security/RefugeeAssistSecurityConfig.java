package com.crossover.hackhathon.config.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.crossover.hackhathon.RefugeeAssistApplication;


/**
 * Security configuration class for Spring Security.
 * 
 * @author murathacioglu.
 */
@Configuration
@ComponentScan({"com.crossover.hackhathon.service"})
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class RefugeeAssistSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(RefugeeAssistSecurityConfig.class);
	
	
	@Autowired
	private UserDetailsService refugeeUserDetailsService;
	
	@Autowired
	private AuthTokenProcessingFilter authTokenProcessingFilter;
	

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationEntryPoint unauthorizedEntryPoint() {
		return new UnauthorizedEntryPoint();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {				
				registry
					.addMapping("/api/**")
					.allowedOrigins("*")
					.allowedMethods("GET", "POST", "DELETE", "HEAD", "PUT", "OPTIONS");
			}
		    @Override
		    public void addViewControllers(ViewControllerRegistry registry) {		    	
		        registry.addViewController("/app").setViewName("redirect:/app/");
		        registry.addViewController("/app/").setViewName("forward:/app/index.html");		        
		        registry.addViewController("/admin").setViewName("redirect:/admin/");
		        registry.addViewController("/register").setViewName("redirect:/register/");
		        super.addViewControllers(registry);
		    }
		};
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(refugeeUserDetailsService).passwordEncoder(passwordEncoder());
	}
    
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(refugeeUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(WebSecurity webSecurity) throws Exception {
		webSecurity.ignoring().antMatchers("/public/**");
		webSecurity.ignoring().antMatchers(HttpMethod.OPTIONS, "/api/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http
	        .csrf().disable()
	        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and()
	        .authorizeRequests()
	        .antMatchers("/").permitAll()
	        .antMatchers("/register/**").permitAll()
	        .antMatchers("/app/**").permitAll()
	        .antMatchers("/admin/**").permitAll()
	        .antMatchers("/fonts/**").permitAll()
	        .antMatchers("/website/**").permitAll()
	        .antMatchers(RefugeeAssistApplication.API_AUTH_PATH + RefugeeAssistApplication.LOGIN_URL).permitAll()
	        .anyRequest().authenticated()
	        .and()
	        .exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint());


        http.addFilterBefore(authTokenProcessingFilter, BasicAuthenticationFilter.class);
        	
        if ("true".equals(System.getProperty("httpsOnly"))) {
            logger.info("Launching the application in HTTPS-only mode.");
            http.requiresChannel().anyRequest().requiresSecure();
        }		
	}

}

