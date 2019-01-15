package com.inovision.commander.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.inovision.commander.model.User;

public interface UserRepository extends CrudRepository<User, Integer>, UserTokenRepository{

	public Optional<User> findByName(String name);
	public Optional<User> findByUserName(String username);
}
