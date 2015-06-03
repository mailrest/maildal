/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.gen;



import org.junit.Assert;
import org.junit.Test;

import com.mailrest.maildal.gen.AccountId;

public class AccountIdTest {

	@Test
	public void testGenerator() {
		
		 String accountId = AccountId.next();
		 
		 System.out.println("accountId = " + accountId);
		 
		 Assert.assertTrue(accountId.startsWith("1"));
		 Assert.assertEquals(11, accountId.length());
		 
	}
	
}
