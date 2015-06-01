/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.util;


public final class AccountId {

	private AccountId() {
	}

	public static String next() {
		return "1" + Integer.toString(nextInt()) + Integer.toString(nextInt());
	}
	
	private static int nextInt() {
		return Math.abs(RandomInstance.INSTANCE.random().nextInt() % 100000);
	}
	
}
