package com.inovision.commander;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.context.annotation.Bean;
import org.springframework.jndi.JndiObjectFactoryBean;

@SpringBootApplication
public class BootCommanderApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootCommanderApplication.class, args);
	}
	
	@Bean
	public TomcatServletWebServerFactory  tomcatFactory() {
		TomcatServletWebServerFactory tomcatServerFactory = new TomcatServletWebServerFactory () {

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
				// TODO Auto-generated method stub
				tomcat.enableNaming();
				return super.getTomcatWebServer(tomcat);
			}
			
		};
		
		return tomcatServerFactory;
	}
	
	@Bean(destroyMethod="")
	public DataSource jndiDataSource() throws IllegalArgumentException, NamingException {
		JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
		bean.setJndiName("java:comp/env/jdbc/ApiTestDS");
		bean.setProxyInterface(DataSource.class);
		bean.setLookupOnStartup(false);
		bean.afterPropertiesSet();
		return (DataSource)bean.getObject();
	}	
	
}
