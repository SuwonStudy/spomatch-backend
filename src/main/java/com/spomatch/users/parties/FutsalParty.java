package com.spomatch.users.parties;

import com.spomatch.users.common.SportsType;
import com.spomatch.users.players.FutsalPlayer;

/**
 * 풋살 파티를 나타냅니다.
 * 
 * @author Seongbin Kim
 *
 */
public class FutsalParty extends Party<FutsalPlayer> {
	
	public FutsalParty() {
	}
	
	public FutsalParty(FutsalPlayer leader, String name) {
		super(leader, name);
	}

	@Override
	public SportsType getSportsType() {
		return SportsType.FUTSAL;
	}
}
