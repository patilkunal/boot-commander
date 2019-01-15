package com.inovision.commander.repository;

import com.inovision.commander.model.User;

public interface UserTokenRepository {
	
	public void updateTokenAccess(User user);
	public void updateUserToken(String user, String token);
	
}
