package com.spomatch.teams;

import com.spomatch.common.SportsType;
import com.spomatch.players.SoccerPlayer;

public class SoccerTeam extends Team<SoccerPlayer> {

	public SoccerTeam() {
	}
	
	public SoccerTeam(SoccerPlayer leader, String name) {
		super(leader, name);
	}

	@Override
	public SportsType getSportsType() {
		return SportsType.SOCCER;
	}
}
