package com.spomatch.players;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.spomatch.common.SportsType;

/**
 * 축구 선수를 나타내는 엔터티입니다.
 * 
 * @author SeongbinKim
 */
@Entity
@DiscriminatorValue("SOCCER")
public class SoccerPlayer extends Player {

	private SoccerPosition preferredPosition;
	
	public SoccerPlayer() {
		this.sportsType = SportsType.SOCCER;
	}
	
	@Override
	protected Position getPreferredPosition() {
		return preferredPosition;
	}

	@Override
	public SportsType getSportsType() {
		return SportsType.SOCCER;
	}

}
