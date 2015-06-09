/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.repository;

import static com.noorq.casser.core.Query.eq;

import java.util.Date;
import java.util.Optional;

import scala.concurrent.Future;

import com.datastax.driver.core.ResultSet;
import com.mailrest.maildal.model.UnsubscribedRecipient;
import com.noorq.casser.core.Casser;
import com.noorq.casser.support.Fun;

public interface UnsubscribedRecipientRepository extends AbstractRepository {

	static final UnsubscribedRecipient unsubscribedRecipient = Casser.dsl(UnsubscribedRecipient.class);
	
	default Future<Optional<Fun.Tuple1<Date>>> isUnsubscribedRecipient(String domainId, String accountId, String email) {
		
		return session()
				.select(unsubscribedRecipient::unsubscribedAt)
				.where(unsubscribedRecipient::domainId, eq(domainId))
				.and(unsubscribedRecipient::accountId, eq(accountId))
				.and(unsubscribedRecipient::email, eq(email.toLowerCase()))
				.single()
				.future();
		
	}
	
	default Future<ResultSet> unsubscribeRecipient(String domainId, String accountId, String email) {
		
		return session()
				.upsert()
				.value(unsubscribedRecipient::domainId, domainId)
				.value(unsubscribedRecipient::accountId, accountId)
				.value(unsubscribedRecipient::email, email.toLowerCase())
				.value(unsubscribedRecipient::unsubscribedAt, new Date())
				.future();
		
	}
	
}
