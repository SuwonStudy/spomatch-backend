package com.spomatch.users.players;

import java.util.List;
import java.util.Objects;

import com.spomatch.users.common.SportsDomainEntity;
import com.spomatch.users.parties.Party;
import com.spomatch.users.teams.Team;

/**
 * 선수의 공통 속성과 행위를 정의합니다.
 * 
 * @author SeongbinKim
 */
public abstract class Player extends SportsDomainEntity {

	protected PlayerId id;
	
	public PlayerId getId() {
		return id;
	}

	public void setId(PlayerId id) {
		this.id = id;
	}
	
	/**
	 * 선수명을 반환합니다.
	 * 
	 * @return 선수명
	 */
	public String getPlayerName() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		
		Player anotherPlayer = (Player) obj;
		
		return this.id.equals(anotherPlayer.id);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	/**
	 * 선호하는 포지션을 반환합니다.
	 */
	protected abstract Position getPreferredPosition();
	
	/**
	 * 소속된 파티들을 반환합니다.
	 */
	protected abstract List<? extends Party<? extends Player>> getBelongingParties();
	
	/**
	 * 소속된 팀들을 반환합니다.
	 */
	protected abstract List<? extends Team<? extends Player>> getBelongingTeams();

	/**
	 * 파티를 생성한다.
	 */
	public abstract Party<? extends Player> createParty(String name);
	
	/**
	 * 팀을 생성한다.
	 */
	public abstract Team<? extends Player> createTeam(String name);

	/**
	 * 플레이어가 최소 하나의 그룹에서라도 리더임을 반환한다.
	 * 
	 * @return 최소 하나의 그룹에서라도 리더이면 true, 아니면 false
	 */
	public boolean isLeaderOfAnyGroup() {
		
		boolean isLeaderOfAnyParty = getBelongingParties()
				.stream()
				.anyMatch(party -> party.getLeader().equals(this));

		boolean isLeaderOfAnyTeam = getBelongingTeams()
				.stream()
				.anyMatch(team -> team.getLeader().equals(this));
		
		return isLeaderOfAnyParty || isLeaderOfAnyTeam;
	}

}
