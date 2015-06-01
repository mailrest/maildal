package com.mailrest.maildal.util;

import java.security.SecureRandom;

public final class AccountId {

	private final static SecureRandom r = new SecureRandom();
	
	private AccountId() {
	}

	public static String next() {
		return "1" + Integer.toString(nextInt()) + Integer.toString(nextInt());
	}
	
	private static int nextInt() {
		return Math.abs(r.nextInt() % 100000);
	}
	
}
