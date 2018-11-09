package com.spomatch.players;

import com.spomatch.common.SportsType;
import com.spomatch.users.UserId;

public interface PlayerService {

	void addPlayerToUser(UserId ownerId, Player newPlayer);

	void deleteById(PlayerId toRemove);
	
	void deletePlayerByUsersSportsType(UserId ownerId, SportsType sportsType);
}
