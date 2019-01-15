package com.inovision.commander.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inovision.commander.exception.NotfoundException;
import com.inovision.commander.model.User;
import com.inovision.commander.repository.UserRepository;

@Component
@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
public class UserService {
	
	private static final String USER_NOT_FOUND_WITH_ID = "User not found with id: ";
	private static final String USER_NOT_FOUND_WITH_NAME = "User not found with name: ";
	private UserRepository userRepository;
	
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User getUser(int id) throws NotfoundException {
		Optional<User> optional  = userRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new NotfoundException(USER_NOT_FOUND_WITH_ID + id);
		}
	}
	
	public User getUserByName(String name) throws NotfoundException {
		Optional<User> optional  = userRepository.findByName(name);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new NotfoundException(USER_NOT_FOUND_WITH_NAME + name);
		}
	}

	@Transactional(readOnly=false)
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	@Transactional(readOnly=false)
	public void updateUserToken(String user, String token) {
		userRepository.updateUserToken(user, token);
	}
	
	@Transactional(readOnly=false)
	public void updateTokenAccess(User user) {
		userRepository.updateTokenAccess(user);
	}
	

}
