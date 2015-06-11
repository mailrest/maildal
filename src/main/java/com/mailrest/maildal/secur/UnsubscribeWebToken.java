/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.secur;

import com.google.gson.JsonObject;


public final class UnsubscribeWebToken implements JsonSerializable<UnsubscribeWebToken> {

	private static final String ACCOUNT_ID_FIELD = "a";
	private static final String DOMAIN_ID_FIELD = "d";
	private static final String EMAIL_ID_FIELD = "e";
	
	private String accountId;
	private String domainId;
	private String emailId;
	
	@Override
	public UnsubscribeWebToken newInstance() {
		return new UnsubscribeWebToken();
	}
	
	public String getAccountId() {
		return accountId;
	}
	
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	public String getDomainId() {
		return domainId;
	}
	
	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}
	
	public String getEmailId() {
		return emailId;
	}
	
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String verifyObj() {
		if (accountId == null) {
			return "missing accountId";
		}
		if (domainId == null) {
			return "missing domainId";
		}
		if (emailId == null) {
			return "missing emailId";
		}
		return null;
	}
	
	@Override
	public String verifyJson(JsonObject jsonObject) {
		if (!jsonObject.has(ACCOUNT_ID_FIELD)) {
			return "missing accountId";
		}
		if (!jsonObject.has(DOMAIN_ID_FIELD)) {
			return "missing domainId";
		}
		if (!jsonObject.has(EMAIL_ID_FIELD)) {
			return "missing emailId";
		}
		return null;
	}

	@Override
	public void readJson(JsonObject jsonObject) {
		this.accountId = jsonObject.getAsJsonPrimitive(ACCOUNT_ID_FIELD).getAsString();
		this.domainId = jsonObject.getAsJsonPrimitive(DOMAIN_ID_FIELD).getAsString();
		this.emailId = jsonObject.getAsJsonPrimitive(EMAIL_ID_FIELD).getAsString();
	}

	@Override
	public void writeJson(JsonObject jsonObject) {
		jsonObject.addProperty(ACCOUNT_ID_FIELD, this.accountId);
		jsonObject.addProperty(DOMAIN_ID_FIELD, this.domainId);
		jsonObject.addProperty(EMAIL_ID_FIELD, this.emailId);
	}

	@Override
	public String toString() {
		return "UnsubscribeWebToken [accountId=" + accountId + ", domainId="
				+ domainId + ", emailId=" + emailId + "]";
	}
	
	
	
}
