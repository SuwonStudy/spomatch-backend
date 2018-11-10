package com.spomatch.players;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class PlayerId implements Serializable {

	private static final long serialVersionUID = -20158338736243153L;
	
	@NotNull
	private Long id;

	// Hibernate
	public PlayerId() {
	}
	
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
