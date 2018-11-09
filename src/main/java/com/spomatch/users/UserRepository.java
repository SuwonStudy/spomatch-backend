package com.spomatch.users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UserId> {
	
	User findByUserAuthentication(UserAuthentication userAuth);
}
