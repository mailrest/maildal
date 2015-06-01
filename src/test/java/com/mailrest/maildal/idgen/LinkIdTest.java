/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.idgen;

import org.junit.Test;

import com.mailrest.maildal.idgen.LinkId;

public class LinkIdTest {

	@Test
	public void test() {
		
		String lid = LinkId.next();
		
		System.out.println("lid = " + lid);
		
		
	}
	
}
