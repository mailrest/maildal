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

import static com.noorq.casser.core.Query.eq;

import java.util.Date;

import scala.Option;
import scala.concurrent.Future;

import com.datastax.driver.core.ResultSet;
import com.mailrest.maildal.model.UnsubscribedRecipient;
import com.noorq.casser.core.Casser;
import com.noorq.casser.support.Fun;

public interface UnsubscribedRecipientRepository extends AbstractRepository {

	static final UnsubscribedRecipient unsubscribedRecipient = Casser.dsl(UnsubscribedRecipient.class);
	
	default Future<Option<Fun.Tuple1<Date>>> isUnsubscribedRecipient(DomainRef domainRef, String email) {
		
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
