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
	
	default Future<Optional<Fun.Tuple1<Date>>> isUnsubscribedRecipient(DomainRef domainRef, String email) {
		
		return session()
				.select(unsubscribedRecipient::unsubscribedAt)
				.where(unsubscribedRecipient::accountId, eq(domainRef.accountId()))
				.and(unsubscribedRecipient::domainId, eq(domainRef.domainId()))
				.and(unsubscribedRecipient::email, eq(email.toLowerCase()))
				.single()
				.future();
		
	}
	
	default Future<ResultSet> unsubscribeRecipient(DomainRef domainRef, String email) {
		
		return session()
				.upsert()
				.value(unsubscribedRecipient::accountId, domainRef.accountId())
				.value(unsubscribedRecipient::domainId, domainRef.domainId())
				.value(unsubscribedRecipient::email, email.toLowerCase())
				.value(unsubscribedRecipient::unsubscribedAt, new Date())
				.future();
		
	}
	
}
