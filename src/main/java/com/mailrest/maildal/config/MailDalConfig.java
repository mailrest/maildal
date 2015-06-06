/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.config;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.policies.ConstantReconnectionPolicy;
import com.mailrest.maildal.model.Account;
import com.noorq.casser.core.Casser;
import com.noorq.casser.core.CasserSession;

public final class MailDalConfig {

	private final CasserSession session;
	
	public MailDalConfig(String host, String keyspace) {
		
		Cluster cluster = Cluster.builder()
			.addContactPoint(host)
			.withReconnectionPolicy(new ConstantReconnectionPolicy(1000))
			.build();
		
		session = Casser.connect(cluster, keyspace)
			.addPackage(Account.class.getPackage().getName())
			.autoUpdate()
			.showCql()
			.get();
	}
	
	public CasserSession session() {
		return session;
	}
	
}
