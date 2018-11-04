package com.spomatch.users;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import com.spomatch.users.common.Location;
import com.spomatch.users.common.SportsType;
import com.spomatch.users.players.FutsalPlayer;
import com.spomatch.users.players.Player;
import com.spomatch.users.players.PlayerId;
import com.spomatch.users.players.SoccerPlayer;

/**
 * 유저 엔터티의 도메인 규칙을 분석하여 스펙을 정의하고, 테스트합니다.
 * TDD 및 given-when-then을 따릅니다.
 * 
 * @author Seongbin Kim
 */
public class TestUserSpec {

	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private Validator validator = factory.getValidator();
	
	@Test
	public void 회원의_종류로는_게스트_일반사용자_운영자_가있다() {
		
		// given & when & then
		assertNotNull(RoleType.GUEST);
		assertNotNull(RoleType.USER);
		assertNotNull(RoleType.ADMIN);
	}

	@Test
	public void 회원가입시의_정보는_검증대상이다_성공() {
		
		// given
		User user = new User();
		String idForLogin = "아이디는5자이상30자미만이어야합니다.";
		String pw = "비밀번호는8자이상100자미만이어야합니다.";
		String name = "이름은2자이상30자미만이어야합니다.";
		
		user.setIdForLogin(idForLogin);
		user.setPw(pw);
		user.setName(name);

		// when
		Set<ConstraintViolation<User>> violations = validator.validate(user);
		
		// then
		assertThat(violations.size(), is(0));
	}
	
	@Test
	public void 회원가입시에_제공_할_수있는_정보에는_ID_PW_이름_선호지역_나이_가있다() {
		
		// given
		User user = new User();
		String idForLogin = "아이디는5자이상30자미만이어야합니다.";
		String pw = "비밀번호는8자이상100자미만이어야합니다.";
		String name = "이름은2자이상30자미만이어야합니다.";
		List<Location> preferredLocations = Arrays.asList(
			new Location("서울시 강남구"),
			new Location("수원시 영통구")
		);
		int age = 22;
		
		user.setIdForLogin(idForLogin);
		user.setPw(pw);
		user.setName(name);
		user.setPreferredLocations(preferredLocations);
		user.setAge(age);
	}
	
	@Test
	public void 회원가입시의_정보는_검증대상이다_아이디_때문에_실패() {
		
		// given
		User user = new User();
		
		// when & then
		user.setIdForLogin(RandomStringUtils.randomAlphanumeric(4));
		assertThat("아이디는 5자 이상 30자 이하이어야 합니다.", validator.validateProperty(user, "idForLogin").size(), is(1));
		
		user.setIdForLogin(RandomStringUtils.randomAlphanumeric(31));
		assertThat("아이디는 5자 이상 30자 이하이어야 합니다.", validator.validateProperty(user, "idForLogin").size(), is(1));
	}

	@Test
	public void 회원가입시의_정보는_검증대상이다_비밀번호_때문에_실패() {
		
		// given
		User user = new User();
		
		// when & then
		user.setPw(RandomStringUtils.randomAlphanumeric(7));
		assertThat("비밀번호는 8자 이상이어야 합니다.", validator.validateProperty(user, "pw").size(), is(1));
		
		user.setPw(RandomStringUtils.randomAlphanumeric(101));
		assertThat("비밀번호는 100자 이하이어야 합니다.", validator.validateProperty(user, "pw").size(), is(1));
	}
	
	@Test
	public void 회원가입시의_정보는_검증대상이다_이름_때문에_실패() {
		
		// given 
		User user = new User();
		
		// when & then
		user.setName(RandomStringUtils.randomAlphanumeric(1));
		assertThat("이름은 2자 이상이어야 합니다.", validator.validateProperty(user, "name").size(), is(1));
		
		user.setName(RandomStringUtils.randomAlphanumeric(31));
		assertThat("이름은 30자 미만이어야 합니다.", validator.validateProperty(user, "name").size(), is(1));
	}
	
	@Test
	public void 회원은_동시에_여러_종목에_대한_프로필을_가질_수_있다() {
		
		// given
		User user = new User();
		Player soccerPlayer = new SoccerPlayer();
		Player futsalPlayer = new FutsalPlayer();
		
		// when
		user.addPlayer(soccerPlayer);
		user.addPlayer(futsalPlayer);
		
		// then
		assertThat(user.getPlayerSize(), is(2));
	}

	// then
	@Test(expected = PlayerOfSameSportsTypeAlreadyExistsException.class)
	public void 회원은_각_종목별로_1개의_플레이어를_생성할_수_있다() {
		
		// given
		User user = new User();
		Player soccerPlayer = new SoccerPlayer();
		
		// when
		user.addPlayer(soccerPlayer);
		user.addPlayer(soccerPlayer);
	}
	
	@Test
	public void 회원은_자신이_보유한_플레이어를_제거할_수_있다_성공() {
		
		// given
		User user = new User();
		
		Player soccerPlayer = new SoccerPlayer();
		soccerPlayer.setId(new PlayerId(Long.valueOf(1)));
		
		user.addPlayer(soccerPlayer);
		
		// when
		user.removePlayerBySportsType(SportsType.SOCCER);
		
		// then
		assertThat(user.getPlayerSize(), is(0));
	}

	// then
	@Test(expected = PlayerIsALeaderOfAnyGroupException.class)
	public void 회원은_자신이_보유한_플레이어를_제거할_수_있다_그룹의_리더여서_실패() {
		
		// given
		User user = new User();
		
		Player soccerPlayer = new SoccerPlayer();
		soccerPlayer.setId(new PlayerId(Long.valueOf(1)));
		
		soccerPlayer.createParty(RandomStringUtils.randomAlphabetic(5)); // Team이어도 상관 없음
		
		user.addPlayer(soccerPlayer);
		
		// when
		user.removePlayerBySportsType(SportsType.SOCCER);
	}
}
