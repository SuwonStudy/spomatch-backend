package com.spomatch.players;

import java.util.Objects;

public class PlayerId {

	private Long id;

	public PlayerId(Long value) {
		this.id = value;
	}
	
	public Long get() {
		return id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		
		PlayerId anotherId = (PlayerId) obj;
		
		return this.id == anotherId.id;
	}
	
    @Override
    public int hashCode() {
    	return Objects.hash(id);
    }
}
