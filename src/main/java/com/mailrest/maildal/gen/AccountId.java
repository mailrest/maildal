/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.gen;

import com.mailrest.maildal.util.RandomInstance;


public final class AccountId {

	public static final int ACCOUNT_MOD = 100000;
	
	private AccountId() {
	}

	public static String next() {
		return "1" + Integer.toString(nextInt()) + Integer.toString(nextInt());
	}
	
	private static int nextInt() {
		return Math.abs(RandomInstance.INSTANCE.random().nextInt() % ACCOUNT_MOD);
	}
	
}
