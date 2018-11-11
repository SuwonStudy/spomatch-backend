package com.spomatch.parties;

import com.spomatch.common.SportsType;
import com.spomatch.players.FutsalPlayer;

/**
 * 풋살 파티를 나타냅니다.
 * 
 * @author Seongbin Kim
 *
 */
public class FutsalParty extends Party<FutsalPlayer> {
	
	public FutsalParty(String name) {
		super(name);
	}
	
	public FutsalParty(FutsalPlayer leader, String name) {
		super(leader, name);
	}

	@Override
	public SportsType initSportsType() {
		return SportsType.FUTSAL;
	}
}
