/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.config;

import com.mailrest.maildal.repository.AbstractRepository;
import com.noorq.casser.core.CasserSession;


public class RepositoryConfig implements AbstractRepository {

	private final CasserSession session;
	
	public RepositoryConfig(MailDalConfig config) {
		this.session = config.session();
	}
	
	@Override
	public CasserSession session() {
		return session;
	}

}
