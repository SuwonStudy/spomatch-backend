package com.spomatch.parties;

import com.spomatch.common.SportsType;
import com.spomatch.players.SoccerPlayer;

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
