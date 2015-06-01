/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.repository;

import java.util.stream.Stream;

import com.datastax.driver.core.ResultSet;
import com.google.common.base.Function;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.mailrest.maildal.idgen.LinkId;
import com.mailrest.maildal.model.CallbackAction;
import com.mailrest.maildal.model.UserLink;
import com.noorq.casser.core.Casser;
import com.noorq.casser.core.Query;
import com.noorq.casser.support.Fun;

public interface UserLinkRepository extends AbstractRepository  {

	static final UserLink userLink = Casser.dsl(UserLink.class);

	default ListenableFuture<Stream<Fun.Tuple2<String, CallbackAction>>> lookup(String linkId) {
		
		return session()
				.select(userLink::email, userLink::action)
				.where(userLink::linkId, Query.eq(linkId))
				.async();
	}
	
	default ListenableFuture<String> create(
			String email, 
			CallbackAction action) {
		
		String linkId = LinkId.next();
		
		ListenableFuture<ResultSet> rsFuture = session()
		.insert()
		.value(userLink::linkId, linkId)
		.value(userLink::email, email)
		.value(userLink::action, action)
		.async();
		
		return Futures.transform(rsFuture, new Function<ResultSet, String>() {

			@Override
			public String apply(ResultSet input) {
				return linkId;
			}
			
		});

	}
}
