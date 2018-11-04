package com.spomatch.users;

public interface UserService {

	User register(User user);

	User updateUserInfo(User toUpdate);

	User updatePlayers(User toUpdate);

	void cancel(UserId idToCancel);

	User getById(UserId id);

}
