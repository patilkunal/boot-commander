package com.inovision.commander.repository;

import com.inovision.commander.model.User;

// Spring JPA, by design, will search for implementation class UserTokenRepositoryImpl
// and autowires in when this interface is extended by any other interface
// Note - no need to annotate this class as @Component or @Repository 
public interface UserTokenRepository {
	
	public void updateTokenAccess(User user);
	public void updateUserToken(String user, String token);
	
}
