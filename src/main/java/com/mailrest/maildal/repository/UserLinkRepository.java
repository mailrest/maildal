/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.repository;

import static com.noorq.casser.core.Query.eq;

import java.util.Optional;

import scala.concurrent.Future;

import com.datastax.driver.core.ResultSet;
import com.mailrest.maildal.gen.LinkId;
import com.mailrest.maildal.model.CallbackAction;
import com.mailrest.maildal.model.UserLink;
import com.noorq.casser.core.Casser;
import com.noorq.casser.support.Fun;

public interface UserLinkRepository extends AbstractRepository  {

	static final UserLink userLink = Casser.dsl(UserLink.class);

	static final int ONE_DAY = 3600 * 24;
	
	default Future<Optional<Fun.Tuple3<String, String, CallbackAction>>> lookupLink(String linkId) {
		
		return session()
				.select(userLink::accountId, userLink::userId, userLink::action)
				.where(userLink::linkId, eq(linkId))
				.single()
				.future();
	}
	
	default Future<Fun.Tuple2<ResultSet, String>> createLink(String accountId,
			String userId, 
			CallbackAction action) {
		
		String linkId = LinkId.next();
		
		return session()
			.insert()
			.value(userLink::linkId, linkId)
			.value(userLink::accountId, accountId)
			.value(userLink::userId, userId)
			.value(userLink::action, action)
			.usingTtl(ONE_DAY)
			.future(linkId);

	}
	
	default Future<ResultSet> dropLink(
			String linkId) {
		
		return session()
			.delete()
			.where(userLink::linkId, eq(linkId))
			.future();

	}
}
