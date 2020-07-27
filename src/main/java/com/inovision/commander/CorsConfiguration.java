package com.inovision.commander;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
//@Configuration
public class CorsConfiguration {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CorsConfiguration.class);

	public WebMvcConfigurer corsConfigurer() {
		LOGGER.debug("CorsConfiguration:corsConfigurer called");
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}
}
