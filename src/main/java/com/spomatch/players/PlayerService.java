package com.spomatch.players;

import com.spomatch.common.SportsType;
import com.spomatch.users.User;

public interface PlayerService {

	void addPlayerToUser(User owner, Player newPlayer);

	void deleteById(Long toRemove);
	
	void deletePlayerByUsersSportsType(User owner, SportsType sportsType);
}
