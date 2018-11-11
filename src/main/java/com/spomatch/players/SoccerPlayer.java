package com.spomatch.players;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

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
	
	@Override
	protected Position getPreferredPosition() {
		return preferredPosition;
	}

	@Transient
	@Override
	protected SportsType getSportsType() {
		return SportsType.SOCCER;
	}

}
