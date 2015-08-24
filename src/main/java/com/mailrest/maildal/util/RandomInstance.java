/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public enum RandomInstance {

	INSTANCE;
	
	public static final String ALGORITHM = "SHA1PRNG";
	
	private final SecureRandom r;
	
	private RandomInstance() {
		try {
			r = SecureRandom.getInstance(ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("default PRG algorithm not found in system " + ALGORITHM);
		}
	}
	
	public int nextNaturalInt(int mod) {
		if (mod <= 0) {
			throw new IllegalArgumentException("invalid mod " + mod);
		}
		return Math.abs(r.nextInt() % mod);
	}
	
	public byte[] nextBytes(int size) {
		byte[] arr = new byte[size];
		r.nextBytes(arr);
		return arr;
	}
	
}
