package com.inovision.commander.repository;

import java.util.Date;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.inovision.commander.model.User;

//Note - no need to annotate this class as @Component or @Repository 
public class UserTokenRepositoryImpl implements UserTokenRepository {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserTokenRepositoryImpl.class);

	@Autowired
	private EntityManager em;
	
	@Override
	public void updateTokenAccess(User user) {
		LOGGER.debug("Updating User token access");
		em.createNamedQuery("User.UpdateTokenAccess").setParameter(2, user.getName()).setParameter(1, new Date()).executeUpdate();
	}

	@Override
	public void updateUserToken(String user, String token) {
		LOGGER.debug("Updating User token to {}", token);
		em.createNamedQuery("User.UpdateToken").setParameter(2, user).setParameter(1, token).executeUpdate();
	}

}
