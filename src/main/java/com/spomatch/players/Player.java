package com.spomatch.players;

import java.util.Objects;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.spomatch.common.SportsDomainEntity;
import com.spomatch.users.User;

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

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	protected User user;

	public Player(String name) {
		super(name);
	}
	
	public User getUser() {
		return user;
	}

	public void assignOwner(User owner) {
		this.user = owner;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this.id == null)
			return false;
		
		if (obj == null)
			return false;

		if (obj instanceof Long)
			return id.equals(obj);

		if (obj instanceof User)
			return id == ((User) obj).getId();
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	/**
	 * 선호하는 포지션을 반환합니다.
	 */
	protected abstract Position initPreferredPosition();

}
