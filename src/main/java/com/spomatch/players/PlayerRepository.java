package com.spomatch.players;

import org.springframework.data.repository.CrudRepository;

import com.spomatch.common.SportsType;
import com.spomatch.users.UserId;

public interface PlayerRepository extends CrudRepository<Player, PlayerId> {

	void deleteByUserIdAndSportsType(UserId userId, SportsType sportsType);
}
