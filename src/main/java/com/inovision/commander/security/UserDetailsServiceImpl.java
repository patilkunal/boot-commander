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
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		Optional<User> optional = userRepository.findByUserName(arg0);
		if(optional.isPresent()) {
			User user = optional.get();
			LOGGER.debug("Found following user after authentication: {}", user);
			return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), Collections.EMPTY_LIST);
		} else {
			LOGGER.warn("User not found for username: " + arg0);
			throw new UsernameNotFoundException(arg0);
		}
	}

}
