package com.spomatch.common;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.spomatch.common.rating.ActivityRating;
import com.spomatch.common.rating.ActivityRatingRank;
import com.spomatch.common.rating.CreditRating;
import com.spomatch.common.rating.CreditRatingRank;

/**
 * 상위 엔터티들의 공통 속성이나 행위를 정의합니다.
 * 
 * @author SeongbinKim
 */
@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class SportsDomainEntity {

	/*
	 * 공통 속성
	 */
	@Length(min = 2, max = 15)
	@NotBlank
	protected String name;

	@Transient
	protected List<Location> preferredLocations = new ArrayList<>();
	
	@NotNull
	@Column(name = "sports_type")
	protected SportsType sportsType;

	@Transient
	protected CreditRating creditRating;

	@Transient
	protected ActivityRating activityRating;
	
	public SportsDomainEntity() {
		this.sportsType = getSportsType();
	}
	
	/**
	 * 선호 지역 목록을 반환합니다.
	 */
	@Transient
	public List<Location> getPrefferedLocations() {
		return preferredLocations;
	}
	
	/**
	 * 운동 종목을 반환합니다.
	 */
	@Transient
	protected abstract SportsType getSportsType();

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
