package com.spomatch.players;

import javax.validation.Validator;

import org.springframework.stereotype.Service;

import com.spomatch.common.SportsType;
import com.spomatch.users.UserId;

@Service
public class DefaultPlayerService implements PlayerService {

	private Validator validator;
	
	private PlayerRepository repo;
	
	public DefaultPlayerService(Validator validator, PlayerRepository repo) {
		this.validator = validator;
		this.repo = repo;
	}
	
	//Set<ConstraintViolation<User>> violations = validator.validate(toRegister);
	
	@Override
	public void addPlayerToUser(UserId ownerId, Player newPlayer) {
		
	}

	@Override
	public void deleteById(PlayerId toRemove) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void deletePlayerByUsersSportsType(UserId ownerId, SportsType sportsType) {
		// TODO Auto-generated method stub
		
	}

}
