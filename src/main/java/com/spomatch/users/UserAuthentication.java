package com.spomatch.users;

import java.util.Objects;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@Embeddable
public class UserAuthentication {

	@Length(min = 5, max = 30)
	@NotBlank
	private String idForLogin;
	
	@Length(min = 8)
	@NotBlank
	private String pw;

	// Hibernate
	protected UserAuthentication() {
	}
	
	public UserAuthentication(String idForLogin, String pw) {
		this.idForLogin = idForLogin;
		this.pw = pw;
	}

	public boolean compareCurrent(String oldPassword) {
		return pw.equals(oldPassword);
	}

	public UserAuthentication changePassword(String newPassword) {
		return new UserAuthentication(idForLogin, newPassword);
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

}
