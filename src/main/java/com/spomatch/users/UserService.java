package com.spomatch.users;

public interface UserService {

	User register(User user);

	void updateUserInfo(UserId id, UserInfo toUpdate);
	
	void changePassword(UserId id, PasswordChangeRequest req);

	void cancel(UserId idToCancel);

	User getById(UserId id);

	boolean login(UserAuthentication userAuthChanged);

}
