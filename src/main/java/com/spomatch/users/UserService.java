package com.spomatch.users;

public interface UserService {

	User register(User user);

	User update(User registered);

	void cancel(User user);

	User getById(UserId id);

}
