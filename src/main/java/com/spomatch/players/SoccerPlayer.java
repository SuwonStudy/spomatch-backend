package com.spomatch.players;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
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
@Access(AccessType.FIELD)
public class SoccerPlayer extends Player {

	@Column(name = "preferred_position")
	private SoccerPosition preferredPosition;

	// Hibernate
	protected SoccerPlayer() {
		super(null);
	}
	
	public SoccerPlayer(String name) {
		super(name);
	}
	
	@Override
	protected Position initPreferredPosition() {
		return preferredPosition;
	}

	@Override
	protected SportsType initSportsType() {
		return SportsType.SOCCER;
	}

}
