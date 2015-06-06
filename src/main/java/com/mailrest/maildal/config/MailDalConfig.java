/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.config;

import com.noorq.casser.core.Casser;
import com.noorq.casser.core.CasserSession;

public final class MailDalConfig {

	private final CasserSession session;
	
	public MailDalConfig(String host) {
		session = Casser.session();
	}
	
	public CasserSession session() {
		return session;
	}
	
}
