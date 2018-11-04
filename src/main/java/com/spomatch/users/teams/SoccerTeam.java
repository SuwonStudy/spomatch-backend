package com.spomatch.users.teams;

import com.spomatch.users.common.SportsType;
import com.spomatch.users.players.SoccerPlayer;

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
