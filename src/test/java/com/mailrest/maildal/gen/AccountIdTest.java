/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.gen;



import org.junit.Assert;
import org.junit.Test;

public class AccountIdTest {

	@Test
	public void testGenerator() {
		
		 String accountId = Generators.ACCOUNT_ID.next();
		 
		 System.out.println("accountId = " + accountId);
		 
		 Assert.assertTrue(accountId.startsWith("1"));
		 //Assert.assertEquals(11, accountId.length());
		 
	}
	
}
