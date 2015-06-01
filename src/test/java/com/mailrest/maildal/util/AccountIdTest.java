/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.util;



import org.junit.Assert;
import org.junit.Test;

public class AccountIdTest {

	@Test
	public void testGenerator() {
		
		 String accountId = AccountId.next();
		 
		 System.out.println("accountId = " + accountId);
		 
		 Assert.assertTrue(accountId.startsWith("1"));
		 Assert.assertEquals(11, accountId.length());
		 
	}
	
}
