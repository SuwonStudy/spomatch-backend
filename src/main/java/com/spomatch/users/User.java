package com.spomatch.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.spomatch.users.common.Location;
import com.spomatch.users.common.SportsType;
import com.spomatch.users.players.Player;

/**
 * 회원을 정의합니다.
 * 
 * 한 회원은 여러 개의 선수 프로필을 가질 수 있습니다.
 * 
 * @author SeongbinKim
 *
 */
public class User {

	@NotNull
	private UserId id;
	
	@Length(min = 5, max = 30)
	@NotBlank
	private String idForLogin;
	
	@Length(min = 8, max = 100)
	@NotBlank
	private String pw;

	@Length(min = 2, max = 30)
	@NotBlank
	private String name;
	
	private int age;
	
	private List<Location> preferredLocations = new ArrayList<>();
	
	/**
	 * 회원은 여러 개의 선수 프로필을 가집니다.
	 */
	private List<Player> players = new ArrayList<>();

	public UserId getId() {
		return id;
	}

	public void setId(UserId id) {
		this.id = id;
	}

	public String getIdForLogin() {
		return idForLogin;
	}

	public void setIdForLogin(String idForLogin) {
		this.idForLogin = idForLogin;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<Location> getPreferredLocations() {
		return preferredLocations;
	}

	public void setPreferredLocations(List<Location> preferredLocations) {
		this.preferredLocations = preferredLocations;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	
	/**
	 * 플레이어를 추가합니다.
	 * 
	 * @param toAdd 추가할 선수 프로필
	 */
	public void addPlayer(Player toAdd) {
		
		assertPlayerOfSameSportsTypeNotExist(toAdd);
		
		players.add(toAdd);
	}

	/**
	 * 플레이어를 추가할 때, 동일한 종목에 대한 플레이어가 이미 존재하는 지 확인합니다.
	 * 
	 * @param toAdd 추가할 플레이어
	 */
	private void assertPlayerOfSameSportsTypeNotExist(Player toAdd) {
		SportsType toAddType = toAdd.getSportsType();
		
		for (Player player : players) {
			if (toAddType == player.getSportsType())
				throw new PlayerOfSameSportsTypeAlreadyExistsException();
		}
	}

	public int getPlayerSize() {
		return players.size();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		
		User anotherPlayer = (User) obj;
		
		return getId().equals(anotherPlayer.getId());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}

	/**
	 * 해당하는 종목을 갖는 플레이어를 제거한다.
	 * 
	 * @param typeToRemove 제거할 플레이어의 종목
	 */
	public void removePlayerBySportsType(SportsType typeToRemove) {
		
		Player toRemove = null;
		
		for (Player player : players) {
			if (player.getSportsType() == typeToRemove)
				toRemove = player;
		}
		
		if (toRemove == null)
			return;
		
		if (toRemove.isLeaderOfAnyGroup())
			throw new PlayerIsALeaderOfAnyGroupException();
		
		players.remove(toRemove);
	}

	public void updateUserInfo(User toUpdate) {
		
		this.pw = toUpdate.pw;
		this.preferredLocations = toUpdate.preferredLocations;
		this.age = toUpdate.age;
	}

}
