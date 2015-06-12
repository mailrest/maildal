/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.util;


public enum Base62 implements AlphabetBase {
	
	INSTANCE;
	
	private final char[] ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
	
	private final int[] index;
	
	private Base62() {
		index = makeIndex(ALPHABET);
	}
	
	@Override
	public char[] alphabet() {
		return ALPHABET;
	}

	@Override
	public int[] index() {
		return index;
	}
	
}
