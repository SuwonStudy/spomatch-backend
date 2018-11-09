package com.spomatch.players;

import static org.mockito.Mockito.mock;

import utils.ValidatorProvider;

public class PlayerServiceFactory {

	private static PlayerRepository repo = mock(PlayerRepository.class);
	
	private static DefaultPlayerService svc = new DefaultPlayerService(ValidatorProvider.get(), repo);
	
	public static PlayerService getInstance() {
		return svc;
	}
	
	public static PlayerRepository getMockRepoInstance() {
		return repo;
	}

}
