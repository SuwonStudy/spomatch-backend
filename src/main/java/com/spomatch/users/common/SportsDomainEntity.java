package com.spomatch.users.common;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.spomatch.users.common.rating.ActivityRating;
import com.spomatch.users.common.rating.ActivityRatingRank;
import com.spomatch.users.common.rating.CreditRating;
import com.spomatch.users.common.rating.CreditRatingRank;

/**
 * 상위 엔터티들의 공통 속성이나 행위를 정의합니다.
 * 
 * @author SeongbinKim
 */
public abstract class SportsDomainEntity {

	/*
	 * 공통 속성
	 */
	@Length(min = 2, max = 15)
	@NotBlank
	protected String name;

	protected List<Location> preferredLocations = new ArrayList<>();
	
	protected SportsType sportsType;
	
	protected CreditRating creditRating;
	
	protected ActivityRating activityRating;
	
	public SportsDomainEntity() {
		this.sportsType = getSportsType();
	}
	
	/**
	 * 선호 지역 목록을 반환합니다.
	 */
	public List<Location> getPrefferedLocations() {
		return preferredLocations;
	}
	
	/**
	 * 운동 종목을 반환합니다.
	 */
	public abstract SportsType getSportsType();

	public int getActivityRatingAsInt() {
		return activityRating.getAsInt();
	}

	public ActivityRatingRank getActivityRatingAsRank() {
		return activityRating.getAsRank();
	}

	public int getCreditRatingAsInt() {
		return creditRating.getAsInt();
	}

	public CreditRatingRank getCreditRatingAsRank() {
		return creditRating.getAsRank();
	}

}
