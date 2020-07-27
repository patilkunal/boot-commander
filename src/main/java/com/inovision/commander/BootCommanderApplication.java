package com.inovision.commander;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication(scanBasePackages = {"com.inovision"})
@EnableAutoConfiguration
//@EnableConfigurationProperties(DatabaseProperties.class)
public class BootCommanderApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(BootCommanderApplication.class);

	public static void main(String[] args) {
		LOGGER.info("Starting the Boot Commander application");
		SpringApplication.run(BootCommanderApplication.class, args);
	}
	/*
	@Bean
	public TomcatServletWebServerFactory  tomcatFactory(@Autowired final DatabaseProperties databaseProperties) {
		return new TomcatServletWebServerFactory () {

			@Override
			protected void postProcessContext(org.apache.catalina.Context context) {
				ContextResource resource = new ContextResource();
				resource.setName("jdbc/ApiTestDS");
				resource.setType(DataSource.class.getName());
				resource.setProperty("driverClassName", databaseProperties.getDriverClassName());
				resource.setProperty("url", databaseProperties.getJdbcUrl());
				resource.setProperty("username", databaseProperties.getUser());
				resource.setProperty("password", databaseProperties.getPassword());
				context.getNamingResources().addResource(resource);
			}
			
			@Override
			protected TomcatWebServer getTomcatWebServer(org.apache.catalina.startup.Tomcat tomcat) {
				tomcat.enableNaming();
				return super.getTomcatWebServer(tomcat);
			}
		};
	}
	
	*/

	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/*
	 * Commenting out since it causes issues while running tests
	 */
	/*
	@Bean
	public JndiObjectFactoryBean jndiDataSource() throws IllegalArgumentException, NamingException {
		JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
		bean.setJndiName("jdbc/myDS");
		bean.setProxyInterface(DataSource.class);
		bean.setLookupOnStartup(true);
		return bean;
	}
	*/


	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
//						.allowCredentials(true)
//						.allowedHeaders("Authorization, Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, " +
//								"Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, x-token")
						.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
						.allowedOrigins("*");
			}
		};
	}

}
