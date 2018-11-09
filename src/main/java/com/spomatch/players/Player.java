package com.spomatch.players;

import java.util.Objects;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;

import com.spomatch.common.SportsDomainEntity;

/**
 * 선수의 공통 속성과 행위를 정의합니다.
 * 
 * @author SeongbinKim
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "sports_type")
public abstract class Player extends SportsDomainEntity {

	@NotNull
	@EmbeddedId
	protected PlayerId id;
	
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

}
