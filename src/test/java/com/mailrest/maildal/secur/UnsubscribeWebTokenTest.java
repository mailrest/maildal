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

import org.junit.Assert;
import org.junit.Test;

public class UnsubscribeWebTokenTest {

	@Test
	public void testValidation() {
		
		UnsubscribeWebToken token = new UnsubscribeWebToken();
		Assert.assertNotNull(token.verifyObj());
		
		token.setAccountId("123");
		Assert.assertNotNull(token.verifyObj());

		token.setDomainId("alex.com");
		Assert.assertNotNull(token.verifyObj());

		token.setEmailId("a@b.c");
		Assert.assertNull(token.verifyObj());
		
	}
	
	@Test
	public void testTokenManager() {
		
		TokenManager<UnsubscribeWebToken> tm = new DefaultTokenManager<UnsubscribeWebToken>(new UnsubscribeWebToken());
		
		UnsubscribeWebToken token = new UnsubscribeWebToken();
		token.setAccountId("123");
		token.setDomainId("alex.com");
		token.setEmailId("a@b.c");

		String jwt = tm.toJwt(token, 20);
		
		System.out.println(jwt);
		
		UnsubscribeWebToken actual = tm.fromJwt(jwt);
		
		Assert.assertEquals(token.getAccountId(), actual.getAccountId());
		Assert.assertEquals(token.getDomainId(), actual.getDomainId());
		Assert.assertEquals(token.getEmailId(), actual.getEmailId());
		
	}
	
}
