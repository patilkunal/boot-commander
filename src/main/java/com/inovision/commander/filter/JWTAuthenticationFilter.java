package com.inovision.commander.filter;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.inovision.commander.security.SecurityConstants.EXPIRATION_TIME;
import static com.inovision.commander.security.SecurityConstants.HEADER_STRING;
import static com.inovision.commander.security.SecurityConstants.SECRET;
import static com.inovision.commander.security.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inovision.commander.model.User;
import com.inovision.commander.service.UserService;;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private static final Logger LOGGER =LoggerFactory.getLogger(JWTAuthenticationFilter.class);
	private static final ObjectMapper MAPPER = new ObjectMapper();
	private AuthenticationManager authenticationManager;
	private UserService userService;

	public JWTAuthenticationFilter(AuthenticationManager authManager, UserService userService) {
		
		this.authenticationManager = authManager;
		this.userService = userService;
	}
	
	@Override
	protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
		boolean required = super.requiresAuthentication(request, response);
		if(required) {
			LOGGER.warn("We need authentication - *****");
			LOGGER.debug(request.getHeader("Authorization"));
		}
		return required;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
        try {
            User creds = MAPPER
                    .readValue(request.getInputStream(), User.class);
            if(LOGGER.isTraceEnabled())
            	LOGGER.trace("JWT Attempting authentication: " + creds);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUserName(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new AuthenticationServiceException(e.getMessage());
        }	
    }
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		String userName = ((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername();
        String token = JWT.create()
                .withSubject(userName)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        userService.updateUserToken(userName, token);
	}
}
