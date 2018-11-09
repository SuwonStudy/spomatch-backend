package com.spomatch.users;

import org.apache.commons.lang3.RandomStringUtils;

public class UserTests {

	public static User createDummy() {
		String idForLogin = RandomStringUtils.randomAlphanumeric(10);
		String pw = RandomStringUtils.randomAlphanumeric(10);
		String name = RandomStringUtils.randomAlphanumeric(5);

		UserAuthentication userAuth = new UserAuthentication(idForLogin, pw);
		UserInfo userInfo = new UserInfo(name, 22, null);
		User toRegister = new User(null, userInfo, userAuth, null);

		return toRegister;
	}

	public static User createDummy(UserAuthentication userAuth) {
		String name = RandomStringUtils.randomAlphanumeric(5);

		UserInfo userInfo = new UserInfo(name, 22, null);
		
		User toRegister = new User(null, userInfo, userAuth, null);

		return toRegister;
	}

	public static User createDummy(UserInfo userInfo) {
		String idForLogin = RandomStringUtils.randomAlphanumeric(10);
		String pw = RandomStringUtils.randomAlphanumeric(10);
		
		UserAuthentication userAuth = new UserAuthentication(idForLogin, pw);
		
		User toRegister = new User(null, userInfo, userAuth, null);

		return toRegister;
	}
}
