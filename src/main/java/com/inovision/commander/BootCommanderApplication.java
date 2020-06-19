package com.inovision.commander;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication(scanBasePackages = {"com.inovision"})
@EnableAutoConfiguration
@EnableConfigurationProperties(DatabaseProperties.class)
public class BootCommanderApplication {

	//.*:\/\/(.*?(?=[:@]))[:@]?(.*?(?=[@]))?@?(.*?):?(\d+)?\/(.*)
	private static final String PATTERN_WITH_USER = ".*:\\/\\/(.*?(?=[:@]))[:@]?(.*?(?=[@]))?@?(.*?):?(\\d+)?\\/(.*)";

	//.*:\/\/(\w+)?:?(\d+)?\/(.*)
	private static final String PATTERN_WITHOUT_USER = ".*:\\/\\/(\\w+)?:?(\\d+)?\\/(.*)";

	private static final String ENV_JDBC_DATABASE_URL = "JDBC_DATABASE_URL";
	private static final String ENV_DATABASE_URL = "DATABASE_URL";
	private static final String ENV_DB_HOSTNAME = "DB_HOSTNAME";
	// Heroku would set DATABASE_URL in format 'postgres://user:pass:host:port/database'
	// we will follow same convention when running on local
	private static final Pattern JDBC_URL_PATTERN = Pattern.compile(".*?:\\/\\/(?:(\\w+):)?(?:(\\w+):)?(\\w+):(?:\\w+)?\\/(\\w+)");

	private static final Logger LOGGER = LoggerFactory.getLogger(BootCommanderApplication.class);

	@Autowired
	private DatabaseProperties databaseProperties;
	
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
				setDBProps(resource);
				context.getNamingResources().addResource(resource);
			}
			
			@Override
			protected TomcatWebServer getTomcatWebServer(org.apache.catalina.startup.Tomcat tomcat) {
				tomcat.enableNaming();
				return super.getTomcatWebServer(tomcat);
			}

			private String getDBHost() {
				return StringUtils.isEmpty(System.getenv(ENV_DB_HOSTNAME)) ? "localhost" : System.getenv(ENV_DB_HOSTNAME);
			}

			private void setDBProps(ContextResource resource) {
				System.out.println("database URL **** " + databaseProperties.databaseUrl);
				String jdbcUrl = System.getenv(ENV_JDBC_DATABASE_URL);
				if(jdbcUrl == null) {
					String dburl = System.getenv(ENV_DATABASE_URL);
					Pattern pattern;
					String host = null;
					String port = null;
					String database = null;
					String user = null;
					String password = null;
					if (dburl.contains("@")) {
						pattern = Pattern.compile(PATTERN_WITH_USER);
						Matcher matcher = pattern.matcher(dburl);
						if (matcher.find()) {
							// we get 5 groups
							user = matcher.group(1);
							password = matcher.group(2);
							host = matcher.group(3);
							port = matcher.group(4) != null ? matcher.group(4) : "5432";
							database = matcher.group(5);
						}
					} else {
						pattern = Pattern.compile(PATTERN_WITHOUT_USER);
						Matcher matcher = pattern.matcher(dburl);
						if (matcher.find()) {
							host = matcher.group(1);
							port = matcher.group(2) != null ? matcher.group(2) : "5432";
						}
					}
					jdbcUrl = String.format("jdbc:postgresql://%s:%s/%s?user=%s&password=%s", host, port, database, user, password);
				}
				resource.setProperty("driverClassName", getDriverClassName(jdbcUrl));
				resource.setProperty("url", jdbcUrl);
			}

			private String getDriverClassName(String jdbcUrl) {
				return jdbcUrl.contains("hsql") ?  "org.hsqldb.jdbcDriver" : "org.postgresql.Driver";
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
}
