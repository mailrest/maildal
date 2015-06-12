/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.util;


public enum Base58 implements AlphabetBase {
	
	INSTANCE;
	
	private final char[] ALPHABET = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz".toCharArray();
	
	private final int[] index;
	
	private Base58() {
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
