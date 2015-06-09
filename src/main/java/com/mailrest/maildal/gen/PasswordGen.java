/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.gen;

import com.mailrest.maildal.util.Base58;
import com.mailrest.maildal.util.RandomInstance;

public final class PasswordGen implements SimpleGenerator {

	private final static int KEY_LENGTH = 80 / 8;
	
	PasswordGen() {
	}
	
	public String next() {
		return Base58.INSTANCE.encode(rnd(KEY_LENGTH));
	}
	
	private static byte[] rnd(int length) {
		byte[] bytes = new byte[length];
		RandomInstance.INSTANCE.random().nextBytes(bytes);
		return bytes;
	}
}
