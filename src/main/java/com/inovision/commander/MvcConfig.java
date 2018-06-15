package com.inovision.commander;

import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * Use this in Spring MVC application
 * for Spring Boot see CorsConfiguration
 */
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/")
			.allowedMethods(
					HttpMethod.GET.name(), 
					HttpMethod.POST.name(), 
					HttpMethod.PUT.name(), 
					HttpMethod.DELETE.name() ).allowedOrigins("http://localhost:4200");
	}
}
