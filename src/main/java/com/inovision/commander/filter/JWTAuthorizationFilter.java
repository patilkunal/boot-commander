package com.inovision.commander.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.inovision.commander.security.SecurityConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static com.inovision.commander.security.SecurityConstants.HEADER_STRING;
import static com.inovision.commander.security.SecurityConstants.TOKEN_PREFIX;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(JWTAuthorizationFilter.class);

	private SecurityConstants securityConstants;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, SecurityConstants securityConstants) {
		super(authenticationManager);
		this.securityConstants = securityConstants;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String header = req.getHeader(HEADER_STRING);
		LOGGER.trace("JWT Inside authorization filter");
		if(header != null && header.startsWith(TOKEN_PREFIX)) {
			UsernamePasswordAuthenticationToken auth = getAuthentication(req);			
			LOGGER.trace("JWT Got authenticaton token {}", auth);
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
				
		chain.doFilter(req, res);
		
	}
	
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req) {
		UsernamePasswordAuthenticationToken authToken = null;
		String token = req.getHeader(HEADER_STRING);
		LOGGER.debug("Authorization: {}", token);
		if(token != null) {
			 DecodedJWT jwt = JWT.require(Algorithm.HMAC512(securityConstants.SECRET.getBytes()))
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
