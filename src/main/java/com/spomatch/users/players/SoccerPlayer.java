package com.spomatch.users.players;

import java.util.ArrayList;
import java.util.List;

import com.spomatch.users.common.SportsType;
import com.spomatch.users.parties.SoccerParty;
import com.spomatch.users.teams.SoccerTeam;

/**
 * 축구 선수를 나타내는 엔터티입니다.
 * 
 * @author SeongbinKim
 */
public class SoccerPlayer extends Player {

	private SoccerPosition preferredPosition;
	private List<SoccerParty> belongingParties = new ArrayList<>();
	private List<SoccerTeam> belongingTeams = new ArrayList<>();
	
	public SoccerPlayer() {
		this.sportsType = SportsType.SOCCER;
	}
	
	@Override
	protected Position getPreferredPosition() {
		return preferredPosition;
	}

	@Override
	protected List<SoccerParty> getBelongingParties() {
		return belongingParties;
	}

	@Override
	protected List<SoccerTeam> getBelongingTeams() {
		return belongingTeams;
	}

	@Override
	public SoccerParty createParty(String name) {
		SoccerParty created = new SoccerParty(this, name);
		belongingParties.add(created);
		return created;
	}

	@Override
	public SoccerTeam createTeam(String name) {
		SoccerTeam created = new SoccerTeam(this, name);
		belongingTeams.add(created);
		return created;
	}

	@Override
	public SportsType getSportsType() {
		return SportsType.SOCCER;
	}

}
