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
package com.mailrest.maildal.gen;

public enum Generators {

	ACCOUNT_ID(new AccountId()),
	API_KEY(new ApiKey()),
	COOKIE_ID(new CookieId()),
	LINK_ID(new LinkId()),
	MESSAGE_ID(new MessageId()),
	PASSWORD(new PasswordGen()),
	PASSWORD_SALT(new PasswordSalt()),
	REQUEST_ID(new RequestId());

	private final Object gen;
	
	private Generators(Object gen) {
		this.gen = gen;
	}
	
	public String next() {
		if (gen instanceof SimpleGenerator) {
			return ((SimpleGenerator)gen).next();
		}
		if (gen instanceof ParametarizedGenerator) {
			return ((ParametarizedGenerator)gen).next("");
		}
		throw new IllegalStateException("unknown generator type " + gen.getClass());
	}
	
	public String next(String parameter) {
		if (gen instanceof SimpleGenerator) {
			return ((SimpleGenerator)gen).next();
		}
		if (gen instanceof ParametarizedGenerator) {
			return ((ParametarizedGenerator)gen).next(parameter);
		}
		throw new IllegalStateException("unknown generator type " + gen.getClass());
	}
	
}
