package com.spomatch.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;

import com.spomatch.players.Player;

/**
 * 회원을 정의합니다.
 * 
 * 한 회원은 여러 개의 선수 프로필을 가질 수 있습니다.
 * 
 * @author SeongbinKim
 *
 */
@Entity
@Table(name="users")
public class User implements Cloneable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Valid
	@Embedded
	private UserInfo userInfo;

	@Valid
	@Embedded
	private UserAuthentication userAuthentication;
	
	/**
	 * 회원은 여러 개의 선수 프로필을 가집니다.
	 */
	@OneToMany(mappedBy = "user")
	private List<Player> players;

	// Hibernate
	protected User() {
	}

	public User(Long id, UserInfo userInfo, UserAuthentication userAuthentication, List<Player> players) {
		this.id = id;
		this.userInfo = userInfo;
		this.userAuthentication = userAuthentication;
		setPlayers(players);
	}

	public void assignId(Long nextId) {
		this.id = nextId;
	}
	
	public Long getId() {
		return id;
	}

	public int getPlayerSize() {
		return players.size();
	}

	public void updateUserInfo(UserInfo toUpdate) {
		this.userInfo = toUpdate;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public UserAuthentication getUserAuthentication() {
		return userAuthentication;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void changePassword(PasswordChangeRequest req) {
		boolean correctPw = userAuthentication.compareCurrent(req.getOldPassword());
		
		if (!correctPw)
			throw new PasswordNotMatchException();
		
		UserAuthentication newAuth = userAuthentication.changePassword(req.getNewPassword());
		this.userAuthentication = newAuth;
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
			return getId() == ((User) obj).getId();
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}
	
	@Override
	public User clone() {
		return new User(id, userInfo, userAuthentication, players);
	}

	private void setPlayers(List<Player> players) {
		if (players == null)
			players = new ArrayList<>();
		
		this.players = players;
	}
	
}
