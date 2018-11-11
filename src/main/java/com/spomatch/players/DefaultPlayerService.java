package com.spomatch.players;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.spomatch.common.SportsType;
import com.spomatch.users.User;

@Transactional
@Service
public class DefaultPlayerService implements PlayerService {

	private PlayerRepository repo;
	
	public DefaultPlayerService(PlayerRepository repo) {
		this.repo = repo;
	}
	
	@Override
	public void addPlayerToUser(User owner, Player newPlayer) {
		repo.save(newPlayer);
		
		newPlayer.assignOwner(owner);
	}

	@Override
	public void deleteById(Long toRemove) {
		repo.deleteById(toRemove);
	}
	
	@Override
	public void deletePlayerByUsersSportsType(User owner, SportsType sportsType) {
		repo.deleteByUserAndSportsType(owner, sportsType);
	}

}
