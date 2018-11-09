package com.spomatch.common;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import com.spomatch.players.Player;

/**
 * Player 집단에서 사용되는 속성을 추상화한 클래스입니다.
 * 
 * @author SeongbinKim
 */
@MappedSuperclass
public abstract class AbstractPlayerGroup<T extends Player> extends SportsDomainEntity {

	/*
	 * 공통 속성
	 */
	@NotNull
	protected T leader;
	
	protected List<T> players;
	
	public AbstractPlayerGroup() {
	}
	
	public AbstractPlayerGroup(T leader, String name) {
		
		this.leader = leader;
		this.players = new ArrayList<>();
		this.players.add(leader);
		this.name = name;
	}
}
