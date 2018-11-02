package com.inovision.commander.filter;

import static com.inovision.commander.security.SecurityConstants.HEADER_STRING;
import static com.inovision.commander.security.SecurityConstants.SECRET;
import static com.inovision.commander.security.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(JWTAuthorizationFilter.class);
	
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String header = req.getHeader(HEADER_STRING);
		
		if(header != null && header.startsWith(TOKEN_PREFIX)) {
			UsernamePasswordAuthenticationToken auth = getAuthentication(req);			
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
				
		chain.doFilter(req, res);
		
	}
	
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req) {
		UsernamePasswordAuthenticationToken authToken = null;
		String token = req.getHeader(HEADER_STRING);
		LOGGER.debug("Authorization: {}", token);
		if(token != null) {
			 DecodedJWT jwt = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
					.build()
					.verify(token.replace(TOKEN_PREFIX, ""));
					String user = jwt.getSubject();
			LOGGER.debug("Parsed user from token: {}", user);
			if(user != null) {
				authToken = new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
			}
		}
		
		return authToken;
	}

}
