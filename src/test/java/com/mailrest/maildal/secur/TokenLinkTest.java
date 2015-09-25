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
