package com.spomatch.users;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

import com.spomatch.users.players.Player;
import com.spomatch.users.players.PlayerId;
import com.spomatch.users.players.SoccerPlayer;

public class UserTests {

	public static User createDummy() {
		String idForLogin = RandomStringUtils.randomAlphanumeric(10);
		String pw = RandomStringUtils.randomAlphanumeric(10);
		String name = RandomStringUtils.randomAlphanumeric(5);

		User toRegister = new User();
		toRegister.setIdForLogin(idForLogin);
		toRegister.setPw(pw);
		toRegister.setName(name);

		return toRegister;
	}

	public static void createLeaderPlayer(User user) {
		
		Random r = new Random();
		long randomId = r.nextLong();
		
		Player soccerPlayer = new SoccerPlayer();
		soccerPlayer.setId(new PlayerId(randomId));

		soccerPlayer.createParty(RandomStringUtils.randomAlphabetic(5)); // Team이어도 상관 없음

		user.addPlayer(soccerPlayer);
	}
}
