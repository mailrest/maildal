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
	
	public SecureRandom random() {
		return r;
	}
	
}
