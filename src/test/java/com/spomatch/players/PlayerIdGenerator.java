package com.spomatch.players;

import java.util.concurrent.atomic.AtomicLong;

import com.mmnaseri.utils.spring.data.domain.KeyGenerator;

public class PlayerIdGenerator implements KeyGenerator<PlayerId> {

    private final AtomicLong seed = new AtomicLong(1);

	@Override
	public PlayerId generate() {
		return new PlayerId(seed.getAndIncrement());
	}

}
