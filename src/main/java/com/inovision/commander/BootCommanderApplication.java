package com.inovision.commander;

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

@SpringBootApplication
@EnableAutoConfiguration
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
				resource.setLookupName("jdbc/ApiTestDS");
				resource.setType(DataSource.class.getName());
				resource.setProperty("driverClassName", "org.hsqldb.jdbcDriver");
				resource.setProperty("url", "jdbc:hsqldb:hsql://localhost:9001/testcasedb");
				context.getNamingResources().addResource(resource);
			}
			
			@Override
			protected TomcatWebServer getTomcatWebServer(org.apache.catalina.startup.Tomcat tomcat) {
				tomcat.enableNaming();
				return super.getTomcatWebServer(tomcat);
			}
			
		};
		
	}
	
	/*
	 * Commenting out since it causes issues while running tests
	@Bean(destroyMethod="")
	public DataSource jndiDataSource() throws IllegalArgumentException, NamingException {
		JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
		bean.setJndiName("java:comp/env/jdbc/ApiTestDS");
		bean.setProxyInterface(DataSource.class);
		bean.setLookupOnStartup(false);
		bean.afterPropertiesSet();
		return (DataSource)bean.getObject();
	}	
	*/
}
