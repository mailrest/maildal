/*
 *      Copyright (C) 2015 Noorq, Inc.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
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
