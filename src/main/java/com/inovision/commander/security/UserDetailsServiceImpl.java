package com.inovision.commander.security;

import java.util.Collections;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.inovision.commander.model.User;
import com.inovision.commander.repository.UserRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	private UserRepository userRepository;
	
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LOGGER.trace("JWT Trying to find user by userName {}", username);
		
		Optional<User> optional = userRepository.findByUsername(username);
		if(optional.isPresent()) {
			User user = optional.get();
			LOGGER.debug("JWT Found following user after authentication: {}", user);
			return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), Collections.EMPTY_LIST);
		} else {
			LOGGER.warn("JWT User not found for username: " + username);
			throw new UsernameNotFoundException(username);
		}
	}

}
