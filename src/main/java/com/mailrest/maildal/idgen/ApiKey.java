/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.idgen;

import com.mailrest.maildal.util.Base62;
import com.mailrest.maildal.util.RandomInstance;

public final class ApiKey {

	private final static int KEY_LENGTH = 128 / 8;
	
	private ApiKey() {
	}

	public static String next() {
		return "key-" + Base62.INSTANCE.encode(rnd(KEY_LENGTH));
	}
	
	private static byte[] rnd(int length) {
		byte[] bytes = new byte[length];
		RandomInstance.INSTANCE.random().nextBytes(bytes);
		return bytes;
	}
	
}
