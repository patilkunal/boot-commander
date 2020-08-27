package com.inovision.commander.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "security")
@Component
public class SecurityConstants {

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String ROLE_CLAIM = "USER_ROLE";

    @Value("${secret:SecretKeyToGenJWTs}")
    public String SECRET;
    @Value("${token_expiration:864000000}") // 10 days
    public long EXPIRATION_TIME;

}
