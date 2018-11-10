package com.spomatch.users;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * Immutable한 Id 객체이다.
 * 
 * @author Seongbin Kim
 */
@Embeddable
public class UserId implements Serializable {

	private static final long serialVersionUID = -8835054677209789858L;
	
	@NotNull
	private Long id;
	
	// Hibernate
	protected UserId() {
	}

	public UserId(Long id) {
		this.id = id;
	}
	
	public Long get() {
		return id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		
		UserId anotherId = (UserId) obj;
		
		return this.id == anotherId.id;
	}
	
    @Override
    public int hashCode() {
    	return Objects.hash(id);
    }
}
