package com.spomatch.users;

import java.util.Objects;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class UserAuthentication {

	@Length(min = 5, max = 30)
	@NotBlank
	private final String idForLogin;
	
	@Length(min = 8)
	@NotBlank
	private final String pw;

	public UserAuthentication(String idForLogin, String pw) {
		this.idForLogin = idForLogin;
		this.pw = pw;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		
		UserAuthentication another = (UserAuthentication) obj;
		
		return idForLogin.equals(another.idForLogin) && pw.equals(another.pw);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(idForLogin, pw);
	}

	public boolean compareCurrent(String oldPassword) {
		return pw.equals(oldPassword);
	}

	public UserAuthentication changePassword(String newPassword) {
		return new UserAuthentication(idForLogin, newPassword);
	}
}
