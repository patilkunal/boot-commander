package com.inovision.commander.repository;

import java.util.Date;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.inovision.commander.model.User;

//Note - no need to annotate this class as @Component or @Repository 
public class UserTokenRepositoryImpl implements UserTokenRepository {

	@Autowired
	private EntityManager em;
	
	@Override
	public void updateTokenAccess(User user) {
		em.createNamedQuery("User.UpdateTokenAccess").setParameter(2, user.getName()).setParameter(1, new Date()).executeUpdate();
	}

	@Override
	public void updateUserToken(String user, String token) {		
		em.createNamedQuery("User.UpdateToken").setParameter(2, user).setParameter(1, token).executeUpdate();
	}

}
