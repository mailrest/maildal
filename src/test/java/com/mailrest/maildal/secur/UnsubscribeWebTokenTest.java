/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.secur;

import org.junit.Assert;
import org.junit.Test;

import com.mailrest.maildal.model.UserPermission;

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
