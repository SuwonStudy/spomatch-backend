package com.spomatch.common;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 지역 개념을 나타내는 값 객체
 */
@Embeddable
public class Location {

	@Column(name = "location")
	private String location;
	
	public Location() {
	}
	
	public Location(String location) {
		this.location = location;
	}
	
	public String getValue() {
		return location;
	}
}
