package com.spomatch.users;

import com.mmnaseri.utils.spring.data.dsl.factory.RepositoryFactoryBuilder;

import utils.ValidatorProvider;

public class UserServiceFactory {

	private static UserServiceFactory instance;

	private UserRepository repo = RepositoryFactoryBuilder
			.builder()
			.generateKeysUsing(UserIdGenerator.class)
			.mock(UserRepository.class);
	
	private DefaultUserService svc = new DefaultUserService(ValidatorProvider.get(), repo);

	public UserService getService() {
		return svc;
	}
	
	public static UserServiceFactory getInstance() {
		if (instance == null)
			instance = new UserServiceFactory();
		return instance;
	}
	
}
