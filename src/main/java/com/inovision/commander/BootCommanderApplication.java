package com.inovision.commander;

import java.util.Collections;

import javax.sql.DataSource;

import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"com.inovision"})
@EnableAutoConfiguration
//@EnableSwagger2
public class BootCommanderApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(BootCommanderApplication.class);
	
	public static void main(String[] args) {
		LOGGER.info("Starting the Boot Commander application");
		SpringApplication.run(BootCommanderApplication.class, args);
	}
	
	@Bean
	public TomcatServletWebServerFactory  tomcatFactory() {
		return new TomcatServletWebServerFactory () {

			@Override
			protected void postProcessContext(org.apache.catalina.Context context) {
				ContextResource resource = new ContextResource();
				resource.setName("jdbc/ApiTestDS");
				resource.setType(DataSource.class.getName());
				resource.setProperty("driverClassName", "org.hsqldb.jdbcDriver");
				resource.setProperty("url", String.format("jdbc:hsqldb:hsql://%s:9001/testcasedb", getDBHost()));
				context.getNamingResources().addResource(resource);
			}
			
			@Override
			protected TomcatWebServer getTomcatWebServer(org.apache.catalina.startup.Tomcat tomcat) {
				tomcat.enableNaming();
				return super.getTomcatWebServer(tomcat);
			}
			
			private String getDBHost() {
				return StringUtils.isEmpty(System.getenv("DB_HOSTNAME")) ? "localhost" : System.getenv("DB_HOSTNAME");  
			}
			
		};
	}
	
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
	/*
	@Bean
	public Docket swaggerConfig() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getApiInfo())
				.select()
				//.paths(PathSelectors.ant("/boot-commander/*"))
				.apis(RequestHandlerSelectors.basePackage("com.inovision.commander"))
				.build();
		
	}
	
	private ApiInfo getApiInfo() {
		return new ApiInfo("Boot Commander API", "REST API for Boot Commander Application", "1.0", "Free to use", 
				new springfox.documentation.service.Contact("Kunal Patil", "http://www.inovisionsoftware.com", "contact@inovisionsoftware.com"), 
				"Apache License", "https://www.apache.org/licenses/LICENSE-2.0.html", Collections.emptyList());
	}

	 */
}
