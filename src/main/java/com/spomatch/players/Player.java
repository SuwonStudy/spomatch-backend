package com.spomatch.players;

import java.util.Objects;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.spomatch.common.SportsDomainEntity;
import com.spomatch.users.UserId;

/**
 * 선수의 공통 속성과 행위를 정의합니다.
 * 
 * @author SeongbinKim
 */
@Entity
@Table(name = "players")
@Access(AccessType.FIELD)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Player extends SportsDomainEntity {

	@NotNull
	@EmbeddedId
	protected PlayerId id;

	@Embedded
    @AttributeOverrides(
            @AttributeOverride(name = "id", column = @Column(name = "user_id"))
    )
	protected UserId userId;

	@Transient
	public UserId getUserId() {
		return userId;
	}

	public void assignToUser(UserId userId) {
		this.userId = userId;
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
	@Transient
	protected abstract Position getPreferredPosition();

}
