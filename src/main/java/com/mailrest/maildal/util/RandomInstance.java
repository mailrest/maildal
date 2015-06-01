/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.util;

import java.security.SecureRandom;

public enum RandomInstance {

	INSTANCE;
	
	private final SecureRandom r;
	
	private RandomInstance() {
		r = new SecureRandom();
	}
	
	public SecureRandom random() {
		return r;
	}
	
}
