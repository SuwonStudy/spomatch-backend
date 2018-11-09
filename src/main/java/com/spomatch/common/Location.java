package com.spomatch.common;

/**
 * 지역 개념을 나타내는 값 객체
 */
public class Location {

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
