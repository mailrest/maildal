/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.util;


import org.junit.Assert;
import org.junit.Test;

public class DomainIdTest {

	@Test
	public void testLowerCase() {
		
		String actual = DomainId.INSTANCE.fromDomain("mailrest.com");

		Assert.assertEquals("mailrest.com", actual);
		
	}
	
	@Test
	public void testUpperCase() {
		
		String actual = DomainId.INSTANCE.fromDomain("mailresT.com");

		Assert.assertEquals("mailrest.com", actual);
		
	}
	
	@Test
	public void testIDN() {
		
		String actual = DomainId.INSTANCE.fromDomain("мейлрест.рф");

		Assert.assertEquals("xn--e1aalic1aii.xn--p1ai", actual);
		
	}
	
}