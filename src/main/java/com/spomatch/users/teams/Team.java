package com.spomatch.users.teams;

import com.spomatch.users.groups.AbstractPlayerGroup;
import com.spomatch.users.players.Player;

/**
 * Team의 공통적인 속성과 행위를 정의합니다.
 * 
 * @author SeongbinKim
 */
public abstract class Team<T extends Player> extends AbstractPlayerGroup<T> {

	public Team() {
	}

	public Team(T leader, String name) {
		super(leader, name);
	}
	
	/**
	 * 팀명을 반환한다.
	 * 
	 * @return 팀명
	 */
	public String getTeamName() {
		return name;
	}
}
