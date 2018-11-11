package com.spomatch.players;

import org.springframework.data.repository.CrudRepository;

import com.spomatch.common.SportsType;
import com.spomatch.users.User;

public interface PlayerRepository extends CrudRepository<Player, Long> {

	void deleteByUserAndSportsType(User owner, SportsType sportsType);
}
