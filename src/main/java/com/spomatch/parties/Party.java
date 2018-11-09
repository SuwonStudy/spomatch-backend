package com.spomatch.parties;

import com.spomatch.common.AbstractPlayerGroup;
import com.spomatch.players.Player;

/**
 * Party의 공통적인 속성과 행위를 정의합니다.
 * 
 * @author SeongbinKim
 */
public abstract class Party<T extends Player> extends AbstractPlayerGroup<T> {

	public Party() {
	}
	
	public Party(T leader, String name) {
		super(leader, name);
	}
	
	/**
	 * 파티명을 반환한다.
	 * 
	 * @return 파티명
	 */
	public String getPartyName() {
		return name;
	}
}
