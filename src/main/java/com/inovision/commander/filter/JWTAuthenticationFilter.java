package com.inovision.commander.filter;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inovision.commander.model.User;
import com.inovision.commander.model.UserRole;
import com.inovision.commander.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.inovision.commander.security.SecurityConstants.*;

;


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
            //TODO: Verify user and pass exists in DB
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
	
	@lombok.SneakyThrows
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		String userName = ((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername();
		User user = userService.getUserByName(userName);
		Set<UserRole> roles = user.getRoles();
		List<String> roleNames = roles.stream().map(ur -> ur.getRole()).collect(Collectors.toList());
        String token = JWT.create()
                .withSubject(userName)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.withClaim(USER_ROLE, roleNames)
				.withIssuer("http://bootcommander.google")
                .sign(HMAC512(SECRET.getBytes()));
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        userService.updateUserToken(userName, token);
	}
}
