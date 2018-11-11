package com.spomatch.users;

public interface UserService {

	User register(User user);

	void updateUserInfo(Long id, UserInfo toUpdate);
	
	void changePassword(Long id, PasswordChangeRequest req);

	void cancel(Long idToCancel);

	User getById(Long id);

	boolean login(UserAuthentication userAuthChanged);

}
