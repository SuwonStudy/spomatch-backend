package com.spomatch.users;

import java.util.Objects;

/**
 * Immutable한 Id 객체이다.
 * 
 * @author Seongbin Kim
 */
public class UserId {

	private Long id;

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
