/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.repository;

import static com.noorq.casser.core.Query.desc;
import static com.noorq.casser.core.Query.eq;

import java.util.Date;
import java.util.Optional;

import scala.concurrent.Future;

import com.datastax.driver.core.ResultSet;
import com.mailrest.maildal.model.DeployedTemplate;
import com.mailrest.maildal.model.Template;
import com.mailrest.maildal.model.TestingTemplate;
import com.noorq.casser.core.Casser;
import com.noorq.casser.support.Fun;

public interface TemplateRepository extends AbstractRepository {

	static final DeployedTemplate deployedTemplate = Casser.dsl(DeployedTemplate.class);
	
	static final TestingTemplate testingTemplate = Casser.dsl(TestingTemplate.class);

	default Future<ResultSet> deployTemplate(String domain, String accountId, String name, Template template) {
		
		return session()
				.upsert()
				.value(deployedTemplate::domain, domain.toLowerCase())
				.value(deployedTemplate::accountId, accountId)
				.value(deployedTemplate::name, name.toLowerCase())
				.value(deployedTemplate::deployedAt, new Date())
				.value(deployedTemplate::template, template)
				.future();
		
	}
	
	default Future<ResultSet> rollbackTemplate(String domain, String accountId, String name, Date deployedAt) {
		
		return session()
				.delete()
				.where(deployedTemplate::domain, eq(domain.toLowerCase()))
				.and(deployedTemplate::accountId, eq(accountId))
				.and(deployedTemplate::name, eq(name.toLowerCase()))
				.and(deployedTemplate::deployedAt, eq(deployedAt))
				.future();
	}
	
	
	default Future<ResultSet> updateTestingTemplate(String domain, String accountId, String name, Template template) {
		
		return session()
				.upsert()
				.value(testingTemplate::domain, domain.toLowerCase())
				.value(testingTemplate::accountId, accountId)
				.value(testingTemplate::name, name.toLowerCase())
				.value(testingTemplate::environment, TestingTemplate.DEFAULT_ENV)
				.value(testingTemplate::template, template)
				.future();
		
	}
	
	default Future<Optional<Fun.Tuple1<Template>>> findTemplate(String domain, String accountId, String name, String env) {
		
		if (DeployedTemplate.DEFAULT_ENV.equals(env)) {
		
			return session()
					.select(deployedTemplate::template)
					.where(deployedTemplate::domain, eq(domain.toLowerCase()))
					.and(deployedTemplate::accountId, eq(accountId))
					.and(deployedTemplate::name, eq(name.toLowerCase()))
					.orderBy(desc(deployedTemplate::deployedAt))
					.single()
					.future();

		}
		else {
			
			return session()
					.select(testingTemplate::template)
					.where(testingTemplate::domain, eq(domain.toLowerCase()))
					.and(testingTemplate::accountId, eq(accountId))
					.and(testingTemplate::name, eq(name.toLowerCase()))
					.and(testingTemplate::environment, eq(env))
					.single()
					.future();
			
		}
		
	}
	

	
}
