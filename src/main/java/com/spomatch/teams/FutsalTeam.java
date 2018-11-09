package com.spomatch.teams;

import com.spomatch.common.SportsType;
import com.spomatch.players.FutsalPlayer;

public class FutsalTeam extends Team<FutsalPlayer> {

	public FutsalTeam() {
	}
	
	public FutsalTeam(FutsalPlayer leader, String name) {
		super(leader, name);
	}

	@Override
	public SportsType getSportsType() {
		return SportsType.FUTSAL;
	}
}
