package com.inovision.commander;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@ConfigurationProperties(prefix = "database")
//@Data
public class DatabaseProperties {

    // Heroku would set DATABASE_URL in format 'postgres://user:pass:host:port/database'
    //.*:\/\/(.*?(?=[:@]))[:@]?(.*?(?=[@]))?@?(.*?):?(\d+)?\/(.*)
    private static final String PATTERN_WITH_USER = ".*:\\/\\/(.*?(?=[:@]))[:@]?(.*?(?=[@]))?@?(.*?):?(\\d+)?\\/(.*)";

    //.*:\/\/(\w+)?:?(\d+)?\/(.*)
    private static final String PATTERN_WITHOUT_USER = ".*:\\/\\/(\\w+)?:?(\\d+)?\\/(.*)";

    String jdbcUrl;
    String user;
    String password;
    String databaseUrl;
    String driverClassName;

    @PostConstruct
    public void postConstruct() {
        if(StringUtils.isEmpty(jdbcUrl) && StringUtils.isNotEmpty(databaseUrl)) {
            Pattern pattern;
            String host = null;
            String port = null;
            String database = null;
            String user = null;
            String password = null;
            if (databaseUrl.contains("@")) {
                pattern = Pattern.compile(PATTERN_WITH_USER);
                Matcher matcher = pattern.matcher(databaseUrl);
                if (matcher.find()) {
                    // we get 5 groups
                    user = StringUtils.isEmpty(user) ? matcher.group(1) : user;
                    password = StringUtils.isEmpty((password)) ? matcher.group(2) : password;
                    host = matcher.group(3);
                    port = matcher.group(4) != null ? matcher.group(4) : "5432";
                    database = matcher.group(5);
                }
            } else {
                pattern = Pattern.compile(PATTERN_WITHOUT_USER);
                Matcher matcher = pattern.matcher(databaseUrl);
                if (matcher.find()) {
                    host = matcher.group(1);
                    port = matcher.group(2) != null ? matcher.group(2) : "5432";
                }
            }
            jdbcUrl = String.format("jdbc:postgresql://%s:%s/%s?user=%s&password=%s", host, port, database, user, password);
            driverClassName = jdbcUrl.contains("hsql") ?  "org.hsqldb.jdbcDriver" : "org.postgresql.Driver";
        }
        if(StringUtils.isEmpty(databaseUrl) && StringUtils.isEmpty(jdbcUrl)) {
            throw new RuntimeException(("database.jdbc-url or database.database-url is required"));
        }
    }

}
