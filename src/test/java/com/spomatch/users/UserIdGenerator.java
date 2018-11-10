package com.spomatch.users;

import java.util.concurrent.atomic.AtomicLong;

import com.mmnaseri.utils.spring.data.domain.KeyGenerator;

public class UserIdGenerator implements KeyGenerator<UserId> {

    private final AtomicLong seed = new AtomicLong(1);

	@Override
	public UserId generate() {
		return new UserId(seed.getAndIncrement());
	}
}
