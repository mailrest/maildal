/*
 *      Copyright (C) 2015 Noorq, Inc.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
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
			.get();
	}
	
	public CasserSession session() {
		return session;
	}
	
}
