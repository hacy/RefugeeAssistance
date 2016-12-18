package com.crossover.hackhathon;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

import com.crossover.hackhathon.controller.AuthenticationController;
import com.crossover.hackhathon.service.ServiceException;

public class RefugeeUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
	
	protected RefugeeUtils(){
		/*
		 * default constructor
		 * */
	}
	
	public static boolean isDevEnv(Environment environment) {
		String[] activeProfiles = environment.getActiveProfiles();
		for (String profile : activeProfiles) {
			if ("dev".equalsIgnoreCase(profile)) {
				return true;
			}				
		}
		return false;
	}

	public static String databaseNameFromJdbcUrl(String url) {
		try {
			URI uri = new URI(url.replace("jdbc:", ""));
			return uri.getPath().substring(1);
		} catch (URISyntaxException e) {
			logger.error("Exception while databaseNameFromJdbcUrl: ",e);
			throw new  ServiceException(e.getMessage());
		}
	}
	
	public static boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		Pattern pattern = java.util.regex.Pattern.compile(ePattern);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
 }
	
	public static void downloadJSON(String filename, String content, 
			HttpServletResponse out) throws IOException {
		download(filename, "application/json", content, out);
	}

	public static void download(String filename, String contentType, 
			String content, HttpServletResponse out) throws IOException {
		out.setContentType(contentType);
		out.setCharacterEncoding("UTF-8");
		String nfilename = filename.replaceAll("[^a-zA-Z0-9.]", "_");
		out.setHeader("Content-Disposition", "attachment;filename=\"" + nfilename + "\"");
		out.getWriter().write(content);
		out.getWriter().flush();
	}

	
	
}
