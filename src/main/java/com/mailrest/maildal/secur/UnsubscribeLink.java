/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.secur;


public final class UnsubscribeLink {

	private UnsubscribeLink() {
	}
	
	public static String generate(String domain, String accountId, String email) {
		return domain + ":" + accountId + ":" + email;
	}
	
}
