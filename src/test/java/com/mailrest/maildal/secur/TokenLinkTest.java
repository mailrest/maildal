/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.secur;


import org.junit.Assert;
import org.junit.Test;

public class TokenLinkTest {

	@Test
	public void testSimple() {
		
		String expected = "abc";

		String actual = TokenLink.INSTANCE.encode(expected);
		Assert.assertEquals(expected, actual);
		
		actual = TokenLink.INSTANCE.decode(expected);
		Assert.assertEquals(expected, actual);
		
	}
	
	@Test
	public void testWithDelimiter() {
		
		String jwt = "ab.c";

		String encoded = TokenLink.INSTANCE.encode(jwt);
		Assert.assertEquals("ab-Dc", encoded);
		
		String actual = TokenLink.INSTANCE.decode(encoded);
		Assert.assertEquals(jwt, actual);
		
	}
	
	@Test
	public void testWithEscape() {
		
		String jwt = "ab-c";

		String encoded = TokenLink.INSTANCE.encode(jwt);
		Assert.assertEquals("ab-Ec", encoded);
		
		String actual = TokenLink.INSTANCE.decode(encoded);
		Assert.assertEquals(jwt, actual);
		
	}
	
	@Test
	public void testWithDelimiterAndEscape() {
		
		String jwt = "ab..--c";

		String encoded = TokenLink.INSTANCE.encode(jwt);
		Assert.assertEquals("ab-D-D-E-Ec", encoded);
		
		String actual = TokenLink.INSTANCE.decode(encoded);
		Assert.assertEquals(jwt, actual);
		
	}
	
}
