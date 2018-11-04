package com.spomatch.users.parties;

import com.spomatch.users.common.SportsType;
import com.spomatch.users.players.SoccerPlayer;

/**
 * 축구 파티를 나타냅니다.
 * 
 * @author SeongbinKim
 */
public class SoccerParty extends Party<SoccerPlayer> {

	public SoccerParty() {
	}
	
	public SoccerParty(SoccerPlayer leader, String name) {
		super(leader, name);
	}

	@Override
	public SportsType getSportsType() {
		return SportsType.SOCCER;
	}
}
