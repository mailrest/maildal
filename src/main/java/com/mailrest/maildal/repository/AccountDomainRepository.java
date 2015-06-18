/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.repository;

import static com.noorq.casser.core.Query.eq;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import scala.concurrent.Future;

import com.datastax.driver.core.ResultSet;
import com.google.common.collect.ImmutableList;
import com.mailrest.maildal.gen.Generators;
import com.mailrest.maildal.model.AccountDomain;
import com.mailrest.maildal.model.DomainSettings;
import com.mailrest.maildal.model.DomainVerificationEvent;
import com.mailrest.maildal.model.DomainVerificationStatus;
import com.mailrest.maildal.model.TrackingOptions;
import com.mailrest.maildal.model.UnsubscribeOptions;
import com.noorq.casser.core.Casser;
import com.noorq.casser.support.Fun;

public interface AccountDomainRepository extends AbstractRepository {

	static final AccountDomain accountDomain = Casser.dsl(AccountDomain.class);
	
	default Future<Stream<AccountDomain>> findDomains(String accountId) {
		
		return session()
				.select(AccountDomain.class)
				.where(accountDomain::accountId, eq(accountId))
				.future();
		
	}
	
	default Future<Optional<List<DomainVerificationEvent>>> getVerificationEvents(String accountId, String domainId) {
		
		return session()
				.select(accountDomain::events)
				.where(accountDomain::accountId, eq(accountId))
				.and(accountDomain::domainId, eq(domainId))
				.single()
				.map(t -> t._1)
				.future();
		
	}
	
	default Future<ResultSet> addVerificationEvent(String accountId, String domainId, DomainVerificationEvent event) {
		
		return session()
				.update()
				.append(accountDomain::events, event)
				.where(accountDomain::accountId, eq(accountId))
				.and(accountDomain::domainId, eq(domainId))
				.future();
		
	}
	
	default Future<Optional<AccountDomain>> findAccountDomain(String accountId, String domainId) {
		
		return session()
				.select(AccountDomain.class)
				.where(accountDomain::accountId, eq(accountId))
				.and(accountDomain::domainId, eq(domainId))
				.single()
				.future();
		
	}
	
	default Future<Optional<Fun.Tuple1<String>>> findApiKey(String accountId, String domainId) {
		
		return session()
				.select(accountDomain::apiKey)
				.where(accountDomain::accountId, eq(accountId))
				.and(accountDomain::domainId, eq(domainId))
				.single()
				.future();
	}
	
	default Future<Fun.Tuple2<ResultSet, String>> updateApiKey(String accountId, String domainId) {
		
		String apiKey = Generators.API_KEY.next();
		
		return session()
				.update()
				.set(accountDomain::apiKey, apiKey)
				.where(accountDomain::accountId, eq(accountId))
				.and(accountDomain::domainId, eq(domainId))
				.future(apiKey);
		
	}
	
	default Future<ResultSet> addAccountDomain(String accountId, String domainId, String domainIdn) {
		
		String apiKey = Generators.API_KEY.next();
		
		final DomainVerificationEvent event = new DomainVerificationEvent() {

			@Override
			public Date eventAt() {
				return new Date();
			}

			@Override
			public DomainVerificationStatus status() {
				return DomainVerificationStatus.ACCEPTED;
			}

			@Override
			public String message() {
				return "accepted";
			}
			
		};
		
		return session()
				.upsert()
				.value(accountDomain::accountId, accountId)
				.value(accountDomain::domainId, domainId)
				.value(accountDomain::domainIdn, domainIdn)
				.value(accountDomain::createdAt, new Date())
				.value(accountDomain::events, ImmutableList.of(event))
				.value(accountDomain::apiKey, apiKey)
				.value(accountDomain::settings, defaultSettings)
				.future();
		
	}
	
	default Future<ResultSet> dropAccountDomain(String accountId, String domainId) {
		
		return session()
				.delete()
				.where(accountDomain::accountId, eq(accountId))
				.and(accountDomain::domainId, eq(domainId))
				.future();
		
	}
	
	static final DomainSettings defaultSettings = new DomainSettings() {

		@Override
		public String testToRecipients() {
			return "";
		}

		@Override
		public String testBccRecipients() {
			return "";
		}

		@Override
		public String prodBccRecipients() {
			return "";
		}

		@Override
		public UnsubscribeOptions unsubscribeLink() {
			return UnsubscribeOptions.NO;
		}

		@Override
		public String unsubscribeText() {
			return "";
		}

		@Override
		public String unsubscribeHtml() {
			return "";
		}

		@Override
		public TrackingOptions trackClicks() {
			return TrackingOptions.NO;
		}

		@Override
		public TrackingOptions trackOpens() {
			return TrackingOptions.NO;
		}
		
	};
	
}
