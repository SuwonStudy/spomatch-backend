package com.spomatch.players;

import com.mmnaseri.utils.spring.data.dsl.factory.RepositoryFactoryBuilder;

import utils.ValidatorProvider;

public class PlayerServiceFactory {

	private static PlayerServiceFactory instance;
	
	private PlayerRepository repo = RepositoryFactoryBuilder
			.builder()
			.generateKeysUsing(PlayerIdGenerator.class)
			.mock(PlayerRepository.class);
	
	private DefaultPlayerService svc = new DefaultPlayerService(ValidatorProvider.get(), repo);
	
	public PlayerService getService() {
		return svc;
	}
	
	public static PlayerServiceFactory getInstance() {
		if (instance == null)
			instance = new PlayerServiceFactory();
		return instance;
	}

}
