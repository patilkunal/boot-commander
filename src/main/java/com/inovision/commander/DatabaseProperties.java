package com.inovision.commander;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@ConfigurationProperties(prefix = "database")
@Data
public class DatabaseProperties {

    String jdbcUrl;
    String user;
    String password;
    String databaseUrl;

}
