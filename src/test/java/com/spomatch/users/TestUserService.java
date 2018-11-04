package com.spomatch.users;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import com.spomatch.users.players.Player;
import com.spomatch.users.players.SoccerPlayer;

/**
 * 유저 서비스의 도메인 규칙을 분석하여 서비스의 스펙을 정의하고, 테스트합니다.
 * 
 * @author Seongbin Kim
 */
public class TestUserService {

	private UserService userService = new DefaultUserService();

	@Test
	public void 회원은_가입_할_수_있다() {
		
		// given
		User toRegister = UserTests.createDummy();
		
		// when
		User registered = userService.register(toRegister);
		
		// then
		assertNotNull(registered.getId());
		assertEquals(toRegister.getIdForLogin(), registered.getIdForLogin());
		assertEquals(toRegister.getPw(), registered.getPw());
		assertEquals(toRegister.getName(), registered.getName());
	}

	// then
	@Test(expected = BadInputException.class)
	public void 회원가입시의_정보는_검증대상이다_아이디가_짧아서_실패() {
		
		// given
		User user = UserTests.createDummy();
		user.setIdForLogin(RandomStringUtils.randomAlphanumeric(4));
		
		// when
		userService.register(user);
	}

	// then
	@Test(expected = BadInputException.class)
	public void 회원가입시의_정보는_검증대상이다_아이디가_길어서_실패() {
		
		// given
		User user = UserTests.createDummy();
		user.setIdForLogin(RandomStringUtils.randomAlphanumeric(31));
		
		// when
		userService.register(user);
	}
	
	// then
	@Test(expected = BadInputException.class)
	public void 회원가입시의_정보는_검증대상이다_비밀번호가_짧아서_실패() {
		
		// given
		User user = UserTests.createDummy();
		user.setPw(RandomStringUtils.randomAlphanumeric(7));
		
		// when
		userService.register(user);
	}

	// then
	@Test(expected = BadInputException.class)
	public void 회원가입시의_정보는_검증대상이다_비밀번호가_길어서_실패() {
		
		// given
		User user = UserTests.createDummy();
		user.setPw(RandomStringUtils.randomAlphanumeric(101));
		
		// when
		userService.register(user);
	}

	// then
	@Test(expected = BadInputException.class)
	public void 회원가입시의_정보는_검증대상이다_이름이_짧아서_실패() {
		
		// given 
		User user = UserTests.createDummy();
		user.setName(RandomStringUtils.randomAlphanumeric(1));
		
		// when
		userService.register(user);
	}

	// then
	@Test(expected = BadInputException.class)
	public void 회원가입시의_정보는_검증대상이다_이름이_길어서_실패() {
		
		// given 
		User user = UserTests.createDummy();
		user.setName(RandomStringUtils.randomAlphanumeric(31));
		
		// when
		userService.register(user);
	}
	
	@Test
	public void 회원은_개인정보를_수정할_수_있다() {
		
		// given
		User registered = registerDummy();
		
		// when
		String newPassword = RandomStringUtils.randomAlphanumeric(10);
		String newIdForLogin = RandomStringUtils.randomAlphanumeric(10);
		String newName = RandomStringUtils.randomAlphanumeric(5);
		
		registered.setPw(newPassword);
		registered.setIdForLogin(newIdForLogin);
		registered.setName(newName);
		
		User updated = userService.updateUserInfo(registered);
		
		// then
		assertEquals(newPassword, updated.getPw());
		
		// ID, 이름의 경우 변경해도 바뀌지 않음
		assertNotEquals(newName, updated.getName());
		assertNotEquals(newIdForLogin, updated.getIdForLogin());
	}

	// then
	@Test(expected = UserNotExistException.class)
	public void 회원은_탈퇴할_수_있다_성공() {
		
		// given
		User user = registerDummy();
		
		// when
		userService.cancel(user.getId());
		
		userService.getById(user.getId());
	}

	// then
	@Test(expected = PlayerExistWhenCancelMembershipException.class)
	public void 회원은_탈퇴할_수_있다_플레이어가_있어서_실패() {
		
		// given
		User user = registerDummy();
		
		Player soccerPlayer = new SoccerPlayer();
		
		user.addPlayer(soccerPlayer);
		
		userService.updatePlayers(user);
		
		// when
		userService.cancel(user.getId());
	}

	private User registerDummy() {
		User toRegister = UserTests.createDummy();
		
		User registered = userService.register(toRegister);
		return registered;
	}
}
