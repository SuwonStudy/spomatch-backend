package com.spomatch.players;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.spomatch.common.SportsType;
import com.spomatch.users.UserId;

@Transactional
@Service
public class DefaultPlayerService implements PlayerService {

	private PlayerRepository repo;
	
	public DefaultPlayerService(PlayerRepository repo) {
		this.repo = repo;
	}
	
	@Override
	public void addPlayerToUser(UserId ownerId, Player newPlayer) {

		newPlayer.assignToUser(ownerId);
		
		repo.save(newPlayer);
	}

	@Override
	public void deleteById(PlayerId toRemove) {
		repo.delete(toRemove);
	}
	
	@Override
	public void deletePlayerByUsersSportsType(UserId ownerId, SportsType sportsType) {
		
		repo.deleteByUserIdAndSportsType(ownerId, sportsType);
	}

}
