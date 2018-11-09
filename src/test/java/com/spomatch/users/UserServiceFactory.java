package com.spomatch.users;

import static org.mockito.Mockito.mock;

import utils.ValidatorProvider;

public class UserServiceFactory {

	private static UserRepository repo = mock(UserRepository.class);
	
	private static DefaultUserService svc = new DefaultUserService(ValidatorProvider.get(), repo);

	public static UserService getInstance() {
		return svc;
	}
	
	public static UserRepository getMockRepoInstance() {
		return repo;
	}
}
