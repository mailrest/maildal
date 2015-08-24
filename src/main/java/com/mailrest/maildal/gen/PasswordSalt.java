/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.gen;

import com.mailrest.maildal.util.Base62;
import com.mailrest.maildal.util.RandomInstance;

public final class PasswordSalt implements SimpleGenerator {

	private final static int KEY_LENGTH = 64 / 8;
	
	PasswordSalt() {
	}
	
	public String next() {
		return Base62.INSTANCE.encode(rnd(KEY_LENGTH));
	}
	
	private static byte[] rnd(int length) {
		return RandomInstance.INSTANCE.nextBytes(length);
	}
}
