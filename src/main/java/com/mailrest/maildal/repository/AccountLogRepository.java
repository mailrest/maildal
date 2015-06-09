/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.repository;

import java.util.Date;
import java.util.stream.Stream;

import scala.concurrent.Future;
import static com.noorq.casser.core.Query.eq;
import static com.noorq.casser.core.Query.lt;
import static com.noorq.casser.core.Query.desc;

import com.datastax.driver.core.ResultSet;
import com.mailrest.maildal.model.AccountAction;
import com.mailrest.maildal.model.AccountLog;
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
	
	default Future<ResultSet> logAccountLogin(String accountId, String ip, String userAgent) {
		
		return session()
				.upsert()
				.value(accountLog::accountId, accountId)
				.value(accountLog::eventAt, new Date())
				.value(accountLog::action, AccountAction.LOGIN)
				.value(accountLog::ip, ip)
				.value(accountLog::userAgent, userAgent)
				.future();
		
	}
	
	default Future<ResultSet> logAccountAction(String accountId, AccountAction action) {
		
		return session()
				.upsert()
				.value(accountLog::accountId, accountId)
				.value(accountLog::eventAt, new Date())
				.value(accountLog::action, action)
				.future();
		
	}
	
	default Future<ResultSet> logTemplateAction(String accountId, AccountAction action, String template) {
		
		return session()
				.upsert()
				.value(accountLog::accountId, accountId)
				.value(accountLog::eventAt, new Date())
				.value(accountLog::action, action)
				.value(accountLog::template, template)
				.future();
		
	}

	default Future<ResultSet> logDomainAction(String accountId, AccountAction action, String domain) {
		
		return session()
				.upsert()
				.value(accountLog::accountId, accountId)
				.value(accountLog::eventAt, new Date())
				.value(accountLog::action, action)
				.value(accountLog::domainId, domain)
				.future();
		
	}

	
}
