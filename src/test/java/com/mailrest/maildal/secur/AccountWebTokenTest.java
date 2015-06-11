/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
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
