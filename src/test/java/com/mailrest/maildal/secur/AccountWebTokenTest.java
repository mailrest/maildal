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

import com.mailrest.maildal.model.UserPermission;

public class AccountWebTokenTest {

	@Test
	public void testValidation() {
		
		AccountWebToken token = new AccountWebToken();
		Assert.assertNotNull(token.verifyObj());
		
		token.setAccountId("123");
		Assert.assertNotNull(token.verifyObj());

		token.setUserId("alex");
		Assert.assertNotNull(token.verifyObj());

		token.setPermission(UserPermission.ADMIN);
		Assert.assertNull(token.verifyObj());
	}
	
	@Test
	public void testTokenManager() {
		
		TokenManager<AccountWebToken> tm = new DefaultTokenManager<AccountWebToken>(new AccountWebToken());
		
		AccountWebToken token = new AccountWebToken();
		token.setAccountId("123");
		token.setUserId("alex");
		token.setPermission(UserPermission.ADMIN);

		String jwt = tm.toJwt(token, 20);
		
		System.out.println(jwt);
		
		AccountWebToken actual = tm.fromJwt(jwt);
		
		Assert.assertEquals(token.getAccountId(), actual.getAccountId());
		Assert.assertEquals(token.getUserId(), actual.getUserId());
		Assert.assertEquals(token.getPermission(), actual.getPermission());
		
	}
	
}
