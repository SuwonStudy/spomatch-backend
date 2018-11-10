package com.spomatch.users;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import com.spomatch.common.SportsType;
import com.spomatch.players.FutsalPlayer;
import com.spomatch.players.Player;
import com.spomatch.players.PlayerService;
import com.spomatch.players.PlayerServiceFactory;
import com.spomatch.players.SoccerPlayer;
import com.spomatch.players.support.PlayerExistWhenCancelMembershipException;
import com.spomatch.players.support.PlayerIsALeaderOfAnyGroupException;
import com.spomatch.players.support.PlayerOfSameSportsTypeAlreadyExistsException;
import com.spomatch.users.support.BadInputException;
import com.spomatch.users.support.UserNotExistException;

/**
 * 유저 서비스의 도메인 규칙을 분석하여 서비스의 스펙을 정의하고, 테스트합니다.
 * 
 * @author Seongbin Kim
 */
public class TestUserService {
	
	private UserService userService;
	private PlayerService playerService;

	@Before
	public void setUp() {
		userService = UserServiceFactory.getInstance().getService();
		playerService = PlayerServiceFactory.getInstance().getService();
	}
	
	@Test
	public void 회원은_가입_할_수_있다() {
		
		// given
		User toRegister = UserTests.createDummy();
		
		// when
		User registered = userService.register(toRegister);
		
		// then
		assertNotNull(registered.getId());
		assertEquals(toRegister.getUserAuthentication(), registered.getUserAuthentication());
		assertEquals(toRegister.getUserInfo(), registered.getUserInfo());
	}

	// then
	@Test(expected = BadInputException.class)
	public void 회원가입시의_정보는_검증대상이다_아이디가_짧아서_실패() {
		
		// given
		UserAuthentication userAuth = createAuthWithCertainLengthOfId(4);
		User user = UserTests.createDummy(userAuth);
		
		// when
		userService.register(user);
	}
	
	// then
	@Test(expected = BadInputException.class)
	public void 회원가입시의_정보는_검증대상이다_아이디가_길어서_실패() {
		
		// given
		UserAuthentication userAuth = createAuthWithCertainLengthOfId(31);
		User user = UserTests.createDummy(userAuth);
		
		// when
		userService.register(user);
	}
	
	// then
	@Test(expected = BadInputException.class)
	public void 회원가입시의_정보는_검증대상이다_비밀번호가_짧아서_실패() {
		
		// given
		UserAuthentication userAuth = createAuthWithCertainLengthOfPw(7);
		User user = UserTests.createDummy(userAuth);
		
		// when
		User registered = userService.register(user);
		
		System.out.println();
	}

	// then
	@Test(expected = BadInputException.class)
	public void 회원가입시의_정보는_검증대상이다_이름이_짧아서_실패() {
		
		// given 
		UserInfo userInfo = createInfoWithCertainLengthOfName(1);
		User user = UserTests.createDummy(userInfo);
		
		// when
		userService.register(user);
	}

	// then
	@Test(expected = BadInputException.class)
	public void 회원가입시의_정보는_검증대상이다_이름이_길어서_실패() {
		
		// given 
		UserInfo userInfo = createInfoWithCertainLengthOfName(31);
		User user = UserTests.createDummy(userInfo);
		
		// when
		userService.register(user);
	}
	
	@Test
	public void 회원은_비밀번호를_수정할_수_있다_성공() {
		
		// given
		String idForLogin = RandomStringUtils.randomAlphanumeric(10);
		String pw = RandomStringUtils.randomAlphanumeric(10);
		UserAuthentication userAuth = new UserAuthentication(idForLogin, pw);
		
		User registered = registerDummy();
		UserId id = registered.getId();
		
		// when
		String newPassword = RandomStringUtils.randomAlphanumeric(10);
		
		userService.changePassword(id, new PasswordChangeRequest(pw, newPassword));
		
		// then
		UserAuthentication userAuthChanged = userAuth.changePassword(newPassword);
		assertTrue(userService.login(userAuthChanged));
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
		
		playerService.addPlayerToUser(user.getId(), soccerPlayer);
		
		// when
		userService.cancel(user.getId());
	}

	@Test
	public void 회원은_동시에_여러_종목에_대한_프로필을_가질_수_있다() {
		
		// given
		User user = registerDummy();
		UserId id = user.getId();
		
		// when
		playerService.addPlayerToUser(id, new SoccerPlayer());
		playerService.addPlayerToUser(id, new FutsalPlayer());
		
		// then
		user = userService.getById(id);
		assertThat(user.getPlayerSize(), is(2));
	}

	// then
	@Test(expected = PlayerOfSameSportsTypeAlreadyExistsException.class)
	public void 회원은_각_종목별로_하나의_플레이어만을_생성할_수_있다() {
		
		// given
		User user = new User();
		
		// when
		for (int i = 0; i < 2; i++)
			playerService.addPlayerToUser(user.getId(), new SoccerPlayer());
	}
	
	@Test
	public void 회원은_자신이_보유한_플레이어를_제거할_수_있다_성공() {
		
		// given
		User user = registerDummy();
		UserId id = user.getId();
		
		playerService.addPlayerToUser(id, new SoccerPlayer());
		
		// when
		playerService.deletePlayerByUsersSportsType(id, SportsType.SOCCER);
		
		// then
		user = userService.getById(id);
		assertThat(user.getPlayerSize(), is(0));
	}

	// then
	@Test(expected = PlayerIsALeaderOfAnyGroupException.class)
	public void 회원은_자신이_보유한_플레이어를_제거할_수_있다_그룹의_리더여서_실패() {
		throw new RuntimeException("구현되지 않은 테스트입니다.");
	}
	
	private User registerDummy() {
		User toRegister = UserTests.createDummy();
		
		User registered = userService.register(toRegister);
		return registered;
	}

	private UserAuthentication createAuthWithCertainLengthOfId(int length) {
		return new UserAuthentication(RandomStringUtils.randomAlphanumeric(length), RandomStringUtils.randomAlphanumeric(10));
	}

	private UserAuthentication createAuthWithCertainLengthOfPw(int length) {
		return new UserAuthentication(RandomStringUtils.randomAlphanumeric(10), RandomStringUtils.randomAlphanumeric(length));
	}

	private UserInfo createInfoWithCertainLengthOfName(int length) {
		return new UserInfo(RandomStringUtils.randomAlphanumeric(length), null);
	}
}
