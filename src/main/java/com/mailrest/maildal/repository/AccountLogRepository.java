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
package com.mailrest.maildal.repository;

import static com.noorq.casser.core.Query.desc;
import static com.noorq.casser.core.Query.eq;
import static com.noorq.casser.core.Query.lt;

import java.util.Date;

import scala.collection.immutable.Stream;
import scala.concurrent.Future;

import com.datastax.driver.core.ResultSet;
import com.mailrest.maildal.model.AccountAction;
import com.mailrest.maildal.model.AccountLog;
import com.mailrest.maildal.model.UserInfo;
import com.noorq.casser.core.Casser;

public interface AccountLogRepository extends AbstractRepository {

	static final AccountLog accountLog = Casser.dsl(AccountLog.class);
	
	default Future<Stream<AccountLog>> getAccountLogs(String accountId, int max) {
		
		return session()
				.select(AccountLog.class)
				.where(accountLog::accountId, eq(accountId))
				.orderBy(desc(accountLog::eventAt))
				.limit(max)
				.future();
		
	}
	
	default Future<Stream<AccountLog>> getAccountLogs(String accountId, Date beforeThen, int max) {
		
		return session()
				.select(AccountLog.class)
				.where(accountLog::accountId, eq(accountId))
				.and(accountLog::eventAt, lt(beforeThen))
				.orderBy(desc(accountLog::eventAt))
				.limit(max)
				.future();
		
	}
	
	default Future<ResultSet> logAccountAction(String accountId, AccountAction action, UserInfo userInfo) {
		
		return session()
				.upsert()
				.value(accountLog::accountId, accountId)
				.value(accountLog::eventAt, new Date())
				.value(accountLog::action, action)
				.value(accountLog::userInfo, userInfo)
				.future();
		
	}
	
	default Future<ResultSet> logTemplateAction(String accountId, AccountAction action, UserInfo userInfo, String template) {
		
		return session()
				.upsert()
				.value(accountLog::accountId, accountId)
				.value(accountLog::eventAt, new Date())
				.value(accountLog::action, action)
				.value(accountLog::userInfo, userInfo)
				.value(accountLog::templateId, template)
				.future();
		
	}

	default Future<ResultSet> logDomainAction(String accountId, AccountAction action, UserInfo userInfo, String domain) {
		
		return session()
				.upsert()
				.value(accountLog::accountId, accountId)
				.value(accountLog::eventAt, new Date())
				.value(accountLog::action, action)
				.value(accountLog::userInfo, userInfo)
				.value(accountLog::domainId, domain)
				.future();
		
	}

	
}
