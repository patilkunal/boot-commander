package com.inovision.commander.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.inovision.commander.model.User;

// Note - this extends UserTokenRepository which defines some custom functions not implemented 
// using Spring JPA 
// Note - no need to annotate this class as @Component or @Repository 
public interface UserRepository extends CrudRepository<User, Integer>, UserTokenRepository{

	public Optional<User> findByName(String name);
	public Optional<User> findByUserName(String username);
}
