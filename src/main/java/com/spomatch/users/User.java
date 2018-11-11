package com.spomatch.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CollectionTable;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;

import com.spomatch.players.PlayerId;

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

	@Valid
	@EmbeddedId
	private UserId id;

	@Valid
	@Embedded
	private UserInfo userInfo;

	@Valid
	@Embedded
	private UserAuthentication userAuthentication;
	
	/**
	 * 회원은 여러 개의 선수 프로필을 가집니다.
	 */
	@OneToMany
	private List<PlayerId> players;

	// Hibernate
	protected User() {
	}

	public User(UserId id, UserInfo userInfo, UserAuthentication userAuthentication, List<PlayerId> players) {
		this.id = id;
		this.userInfo = userInfo;
		this.userAuthentication = userAuthentication;
		setPlayers(players);
	}

	public void assignNewId(UserId nextId) {
		this.id = nextId;
	}
	
	public UserId getId() {
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

	public List<PlayerId> getPlayers() {
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
		
		if (obj instanceof UserId)
			return id.equals(obj);
		
		if (obj instanceof User)
			return getId().equals(((User) obj).getId());
		
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

	private void setPlayers(List<PlayerId> players) {
		if (players == null)
			players = new ArrayList<>();
		
		this.players = players;
	}
	
}
