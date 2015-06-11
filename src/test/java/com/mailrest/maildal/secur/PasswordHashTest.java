/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.secur;

import org.junit.Assert;
import org.junit.Test;

public class PasswordHashTest {

	@Test
	public void test() {
		
		String hash = PasswordHash.INSTANCE.calculate("123");
		
		System.out.println("hash = " + hash);
		
		Assert.assertTrue(PasswordHash.INSTANCE.isEquals(hash, "123"));
		
		Assert.assertFalse(PasswordHash.INSTANCE.isEquals(hash, "1234"));
	}
	
}
