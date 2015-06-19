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

	default Future<ResultSet> deployTemplate(String accountId, String domainId, String templateId, Template template) {
		
		return session()
				.upsert()
				.value(deployedTemplate::accountId, accountId)
				.value(deployedTemplate::domainId, domainId)
				.value(deployedTemplate::templateId, templateId)
				.value(deployedTemplate::deployedAt, new Date())
				.value(deployedTemplate::template, template)
				.future();
		
	}
	
	default Future<ResultSet> rollbackTemplate(String accountId, String domainId, String templateId, Date deployedAt) {
		
		return session()
				.delete()
				.where(deployedTemplate::accountId, eq(accountId))
				.and(deployedTemplate::domainId, eq(domainId))
				.and(deployedTemplate::templateId, eq(templateId))
				.and(deployedTemplate::deployedAt, eq(deployedAt))
				.future();
	}
	
	
	default Future<ResultSet> updateTestingTemplate(String accountId, String domainId, String templateId, String env, Template template) {
		
		return session()
				.upsert()
				.value(testingTemplate::accountId, accountId)
				.value(testingTemplate::domainId, domainId)
				.value(testingTemplate::templateId, templateId)
				.value(testingTemplate::environment, env)
				.value(testingTemplate::template, template)
				.future();
		
	}
	
	default Future<ResultSet> deleteTestingTemplate(String accountId, String domainId, String templateId, String env) {
		
		return session()
				.delete()
				.where(testingTemplate::accountId, eq(accountId))
				.and(testingTemplate::domainId, eq(domainId))
				.and(testingTemplate::templateId, eq(templateId))
				.and(testingTemplate::environment, eq(env))
				.future();
		
	}
	
	default Future<Optional<Fun.Tuple2<Template, Date>>> findDeployedTemplate(String accountId, String domainId, String templateId) {

		return session()
				.select(deployedTemplate::template, deployedTemplate::deployedAt)
				.where(deployedTemplate::accountId, eq(accountId))
				.and(deployedTemplate::domainId, eq(domainId))
				.and(deployedTemplate::templateId, eq(templateId))
				.orderBy(desc(deployedTemplate::deployedAt))
				.single()
				.future();

	}
	
	default Future<Optional<Fun.Tuple1<Template>>> findTestingTemplate(String accountId, String domainId, String templateId, String env) {
		
		return session()
				.select(testingTemplate::template)
				.where(testingTemplate::accountId, eq(accountId))
				.and(testingTemplate::domainId, eq(domainId))
				.and(testingTemplate::templateId, eq(templateId))
				.and(testingTemplate::environment, eq(env))
				.single()
				.future();
			
	}
	

	
}
