package com.spomatch.players;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.spomatch.common.SportsType;

@Entity
@DiscriminatorValue("FUTSAL")
public class FutsalPlayer extends Player {

	private FutsalPosition preferredPosition;
	
	@Override
	protected Position getPreferredPosition() {
		return preferredPosition;
	}

	@Override
	public SportsType getSportsType() {
		return SportsType.FUTSAL;
	}

}
