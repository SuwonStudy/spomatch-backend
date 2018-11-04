package com.spomatch.users.teams;

import com.spomatch.users.common.SportsType;
import com.spomatch.users.players.FutsalPlayer;

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
