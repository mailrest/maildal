/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.repository;

import static com.noorq.casser.core.Query.eq;

import java.util.Date;
import java.util.List;

import scala.Option;
import scala.collection.immutable.Stream;
import scala.concurrent.Future;

import com.datastax.driver.core.ResultSet;
import com.google.common.collect.ImmutableList;
import com.mailrest.maildal.gen.Generators;
import com.mailrest.maildal.model.Domain;
import com.mailrest.maildal.model.DomainSettings;
import com.mailrest.maildal.model.DomainVerificationEvent;
import com.mailrest.maildal.model.DomainVerificationStatus;
import com.mailrest.maildal.model.TrackingOptions;
import com.mailrest.maildal.model.UnsubscribeOptions;
import com.noorq.casser.core.Casser;
import com.noorq.casser.support.Fun;

public interface DomainRepository extends AbstractRepository {

	static final Domain domain = Casser.dsl(Domain.class);
	
	default Future<Stream<Domain>> findDomains(String accountId) {
		
		return session()
				.select(Domain.class)
				.where(domain::accountId, eq(accountId))
				.future();
		
	}
	
	default Future<Option<List<DomainVerificationEvent>>> getVerificationEvents(DomainRef domainRef) {
		
		return session()
				.select(domain::events)
				.where(domain::accountId, eq(domainRef.accountId()))
				.and(domain::domainId, eq(domainRef.domainId()))
				.single()
				.map(t -> t._1)
				.future();
		
	}
	
	default Future<ResultSet> addVerificationEvent(DomainRef domainRef, DomainVerificationEvent event) {
		
		return session()
				.update()
				.append(domain::events, event)
				.where(domain::accountId, eq(domainRef.accountId()))
				.and(domain::domainId, eq(domainRef.domainId()))
				.future();
		
	}
	
	default Future<Option<Domain>> findDomain(DomainRef domainRef) {
		
		return session()
				.select(Domain.class)
				.where(domain::accountId, eq(domainRef.accountId()))
				.and(domain::domainId, eq(domainRef.domainId()))
				.single()
				.future();
		
	}
	
	default Future<Option<Fun.Tuple1<String>>> findApiKey(DomainRef domainRef) {
		
		return session()
				.select(domain::apiKey)
				.where(domain::accountId, eq(domainRef.accountId()))
				.and(domain::domainId, eq(domainRef.domainId()))
				.single()
				.future();
	}
	
	default Future<Fun.Tuple2<ResultSet, String>> updateApiKey(DomainRef domainRef) {
		
		String apiKey = Generators.API_KEY.next();
		
		return session()
				.update()
				.set(domain::apiKey, apiKey)
				.where(domain::accountId, eq(domainRef.accountId()))
				.and(domain::domainId, eq(domainRef.domainId()))
				.future(apiKey);
		
	}
	
	default Future<ResultSet> addDomain(DomainRef domainRef, String domainIdn) {
		
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
				.value(domain::accountId, domainRef.accountId())
				.value(domain::domainId, domainRef.domainId())
				.value(domain::domainIdn, domainIdn)
				.value(domain::createdAt, new Date())
				.value(domain::events, ImmutableList.of(event))
				.value(domain::apiKey, apiKey)
				.value(domain::settings, defaultSettings)
				.future();
		
	}
	
	default Future<ResultSet> dropDomain(DomainRef domainRef) {
		
		return session()
				.delete()
				.where(domain::accountId, eq(domainRef.accountId()))
				.and(domain::domainId, eq(domainRef.domainId()))
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
