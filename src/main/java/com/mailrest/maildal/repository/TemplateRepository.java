/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.repository;

import static com.noorq.casser.core.Query.desc;
import static com.noorq.casser.core.Query.eq;

import java.util.Date;

import scala.Option;
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

	default Future<ResultSet> deployTemplate(TemplateRef templateRef, Template template) {
		
		return session()
				.upsert()
				.value(deployedTemplate::accountId, templateRef.accountId())
				.value(deployedTemplate::domainId, templateRef.domainId())
				.value(deployedTemplate::templateId, templateRef.templateId())
				.value(deployedTemplate::deployedAt, new Date())
				.value(deployedTemplate::template, template)
				.future();
		
	}
	
	default Future<ResultSet> rollbackTemplate(TemplateRef templateRef, Date deployedAt) {
		
		return session()
				.delete()
				.where(deployedTemplate::accountId, eq(templateRef.accountId()))
				.and(deployedTemplate::domainId, eq(templateRef.domainId()))
				.and(deployedTemplate::templateId, eq(templateRef.templateId()))
				.and(deployedTemplate::deployedAt, eq(deployedAt))
				.future();
	}
	
	
	default Future<ResultSet> updateTestingTemplate(TemplateRef templateRef, String env, Template template) {
		
		return session()
				.upsert()
				.value(testingTemplate::accountId, templateRef.accountId())
				.value(testingTemplate::domainId, templateRef.domainId())
				.value(testingTemplate::templateId, templateRef.templateId())
				.value(testingTemplate::environment, env)
				.value(testingTemplate::template, template)
				.future();
		
	}
	
	default Future<ResultSet> deleteTestingTemplate(TemplateRef templateRef, String env) {
		
		return session()
				.delete()
				.where(testingTemplate::accountId, eq(templateRef.accountId()))
				.and(testingTemplate::domainId, eq(templateRef.domainId()))
				.and(testingTemplate::templateId, eq(templateRef.templateId()))
				.and(testingTemplate::environment, eq(env))
				.future();
		
	}
	
	default Future<Option<Fun.Tuple2<Template, Date>>> findDeployedTemplate(TemplateRef templateRef) {

		return session()
				.select(deployedTemplate::template, deployedTemplate::deployedAt)
				.where(deployedTemplate::accountId, eq(templateRef.accountId()))
				.and(deployedTemplate::domainId, eq(templateRef.domainId()))
				.and(deployedTemplate::templateId, eq(templateRef.templateId()))
				.orderBy(desc(deployedTemplate::deployedAt))
				.single()
				.future();

	}
	
	default Future<Option<Fun.Tuple1<Template>>> findTestingTemplate(TemplateRef templateRef, String env) {
		
		return session()
				.select(testingTemplate::template)
				.where(testingTemplate::accountId, eq(templateRef.accountId()))
				.and(testingTemplate::domainId, eq(templateRef.domainId()))
				.and(testingTemplate::templateId, eq(templateRef.templateId()))
				.and(testingTemplate::environment, eq(env))
				.single()
				.future();
			
	}
	

	
}
