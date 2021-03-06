/*
 *      Copyright (C) 2015 Noorq, Inc.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.mailrest.maildal.secur;

import com.google.gson.JsonObject;
import com.mailrest.maildal.model.UserPermission;

public final class AccountWebToken implements JsonSerializable<AccountWebToken> {

	private static final String ACCOUNT_ID_FIELD = "a";
	private static final String USER_ID_FIELD = "u";
	private static final String PERMISSION_FIELD = "p";

	private String accountId;
	private String userId;
	private UserPermission permission;
	
	public AccountWebToken() {
	}

	public AccountWebToken(String accountId, String userId, UserPermission permission) {
		this.accountId = accountId;
		this.userId = userId;
		this.permission = permission;
	}
	
	@Override
	public AccountWebToken newInstance() {
		return new AccountWebToken();
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

	public UserPermission getPermission() {
		return permission;
	}

	public void setPermission(UserPermission permission) {
		this.permission = permission;
	}
	
	@Override
	public String verifyObj() {
		if (accountId == null) {
			return "missing accountId";
		}
		if (userId == null) {
			return "missing userId";
		}
		if (permission == null) {
			return "missing permission";
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
		if (!jsonObject.has(PERMISSION_FIELD)) {
			return "missing permission";
		}
		return null;
	}

	@Override
	public void readJson(JsonObject jsonObject) {
		this.accountId = jsonObject.getAsJsonPrimitive(ACCOUNT_ID_FIELD).getAsString();
		this.userId = jsonObject.getAsJsonPrimitive(USER_ID_FIELD).getAsString();
		this.permission = UserPermission.fromCode(jsonObject.getAsJsonPrimitive(PERMISSION_FIELD).getAsString());
	}

	@Override
	public void writeJson(JsonObject jsonObject) {
		jsonObject.addProperty(ACCOUNT_ID_FIELD, this.accountId);
		jsonObject.addProperty(USER_ID_FIELD, this.userId);
		jsonObject.addProperty(PERMISSION_FIELD, this.permission.getCode());
	}

	@Override
	public String toString() {
		return "AccountWebToken [accountId=" + accountId + ", userId=" + userId
				+ ", permission=" + permission + "]";
	}

}
