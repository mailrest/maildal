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

	default Future<ResultSet> deployTemplate(String domainId, String accountId, String templateId, Template template) {
		
		return session()
				.upsert()
				.value(deployedTemplate::domainId, domainId)
				.value(deployedTemplate::accountId, accountId)
				.value(deployedTemplate::templateId, templateId)
				.value(deployedTemplate::deployedAt, new Date())
				.value(deployedTemplate::template, template)
				.future();
		
	}
	
	default Future<ResultSet> rollbackTemplate(String domainId, String accountId, String templateId, Date deployedAt) {
		
		return session()
				.delete()
				.where(deployedTemplate::domainId, eq(domainId))
				.and(deployedTemplate::accountId, eq(accountId))
				.and(deployedTemplate::templateId, eq(templateId))
				.and(deployedTemplate::deployedAt, eq(deployedAt))
				.future();
	}
	
	
	default Future<ResultSet> updateTestingTemplate(String domainId, String accountId, String templateId, Template template) {
		
		return session()
				.upsert()
				.value(testingTemplate::domainId, domainId)
				.value(testingTemplate::accountId, accountId)
				.value(testingTemplate::templateId, templateId)
				.value(testingTemplate::environment, TestingTemplate.DEFAULT_ENV)
				.value(testingTemplate::template, template)
				.future();
		
	}
	
	default Future<Optional<Fun.Tuple1<Template>>> findTemplate(String domainId, String accountId, String templateId, String env) {
		
		if (DeployedTemplate.DEFAULT_ENV.equals(env)) {
		
			return session()
					.select(deployedTemplate::template)
					.where(deployedTemplate::domainId, eq(domainId))
					.and(deployedTemplate::accountId, eq(accountId))
					.and(deployedTemplate::templateId, eq(templateId))
					.orderBy(desc(deployedTemplate::deployedAt))
					.single()
					.future();

		}
		else {
			
			return session()
					.select(testingTemplate::template)
					.where(testingTemplate::domainId, eq(domainId))
					.and(testingTemplate::accountId, eq(accountId))
					.and(testingTemplate::templateId, eq(templateId))
					.and(testingTemplate::environment, eq(env))
					.single()
					.future();
			
		}
		
	}
	

	
}
