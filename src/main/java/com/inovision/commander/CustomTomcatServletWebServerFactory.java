package com.inovision.commander;

import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;

public class CustomTomcatServletWebServerFactory extends TomcatServletWebServerFactory {

	@Override
	protected void postProcessContext(org.apache.catalina.Context context) {
		ContextResource resource = new ContextResource();
		resource.setName("jdbc/ApiTestDS");
		resource.setType("javax.sql.DataSource");
		resource.setProperty("driverClassName", "org.hsqldb.jdbcDriver");
		resource.setProperty("url", String.format("jdbc:hsqldb:hsql://%s:9001/testcasedb", getDBHost()));
		resource.setProperty("username", "sa");
		resource.setProperty("password", "");
		resource.setAuth("Container");
		resource.setScope("Shareable");
		context.getNamingResources().addResource(resource);
	}
	
	
	
	@Override
	protected TomcatWebServer getTomcatWebServer(org.apache.catalina.startup.Tomcat tomcat) {
		tomcat.enableNaming();
		return super.getTomcatWebServer(tomcat);
	}
	
	private String getDBHost() {
		return (System.getenv("DB_HOSTNAME") == null) ? "localhost" : System.getenv("DB_HOSTNAME");  
	}
	
}
