/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.secur;

import com.google.gson.JsonObject;
import com.mailrest.maildal.model.CallbackAction;

public final class CallbackWebToken implements JsonSerializable<CallbackWebToken> {

	private static final String ACCOUNT_ID_FIELD = "a";
	private static final String USER_ID_FIELD = "u";
	private static final String ACTION_FIELD = "c";

	private String accountId;
	private String userId;
	private CallbackAction action;
	
	@Override
	public CallbackWebToken newInstance() {
		return new CallbackWebToken();
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public CallbackAction getPermission() {
		return action;
	}

	public void setPermission(CallbackAction action) {
		this.action = action;
	}
	
	@Override
	public String verifyObj() {
		if (accountId == null) {
			return "missing accountId";
		}
		if (userId == null) {
			return "missing userId";
		}
		if (action == null) {
			return "missing action";
		}
		return null;
	}

	@Override
	public String verifyJson(JsonObject jsonObject) {
		if (!jsonObject.has(ACCOUNT_ID_FIELD)) {
			return "missing accountId";
		}
		if (!jsonObject.has(USER_ID_FIELD)) {
			return "missing userId";
		}
		if (!jsonObject.has(ACTION_FIELD)) {
			return "missing action";
		}
		return null;
	}

	@Override
	public void readJson(JsonObject jsonObject) {
		this.accountId = jsonObject.getAsJsonPrimitive(ACCOUNT_ID_FIELD).getAsString();
		this.userId = jsonObject.getAsJsonPrimitive(USER_ID_FIELD).getAsString();
		this.action = CallbackAction.fromCode(jsonObject.getAsJsonPrimitive(ACTION_FIELD).getAsString());
	}

	@Override
	public void writeJson(JsonObject jsonObject) {
		jsonObject.addProperty(ACCOUNT_ID_FIELD, this.accountId);
		jsonObject.addProperty(USER_ID_FIELD, this.userId);
		jsonObject.addProperty(ACTION_FIELD, this.action.getCode());
	}

	@Override
	public String toString() {
		return "CallbackWebToken [accountId=" + accountId + ", userId=" + userId
				+ ", action=" + action + "]";
	}

}
