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
package com.mailrest.maildal.support;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class RecipientsTest {

	@Test(expected=IllegalArgumentException.class)
	public void testSingleNull() {
		
		Recipients.parseSingle(null);
		
	}
	

	@Test(expected=IllegalArgumentException.class)
	public void testMultiNull() {
		
		Recipients.parseMulti(null, false);
		
	}
	
	@Test(expected=InvalidEmailException.class)
	public void testSingleEmpty() {
		
		Recipients.parseSingle("");
		
	}
	
	
	@Test
	public void testMultiEmpty() {
		
		Assert.assertEquals(0, Recipients.parseMulti(",,,", false).size());
		
	}
	
	@Test
	public void testSingleAddressValid() {
		
		Recipient rec = Recipients.parseSingle("a@albertshift.com");
		
		Assert.assertFalse(rec.getName().isPresent());
		Assert.assertEquals("a@albertshift.com", rec.getEmail());
		
	}
	
	
	@Test
	public void testSingleNameAddressValid() {
		
		Recipient rec = Recipients.parseSingle("Albert Shift <a@albertshift.com>");
		
		Assert.assertEquals("Albert Shift", rec.getName().get());
		Assert.assertEquals("a@albertshift.com", rec.getEmail());
		
	}
	
	@Test
	public void testSingleNameAddressWithSingleQuoteValid() {
		
		Recipient rec = Recipients.parseSingle("'Albert Shift' <a@albertshift.com>");
		
		Assert.assertEquals("Albert Shift", rec.getName().get());
		Assert.assertEquals("a@albertshift.com", rec.getEmail());
		
	}
	
	@Test
	public void testSingleNameAddressWithDoubleSingleQuoteValid() {
		
		Recipient rec = Recipients.parseSingle("\"Albert Shift\" <a@albertshift.com>");
		
		Assert.assertEquals("Albert Shift", rec.getName().get());
		Assert.assertEquals("a@albertshift.com", rec.getEmail());
		
	}
	
	
	@Test
	public void testMultiNameAddressWithDoubleSingleQuoteValid() {
		
		List<Recipient> list = Recipients.parseMulti("\"Albert Shift\" <a@albertshift.com>, 'Albert Shift' <a@albertshift.com>", false);
		
		for (Recipient rec : list) {
			Assert.assertEquals("Albert Shift", rec.getName().get());
			Assert.assertEquals("a@albertshift.com", rec.getEmail());
		}
		
	}
	
	@Test
	public void testMultiNameAddressWithDoubleSingleQuoteValidWithErrors() {
		
		List<Recipient> list = Recipients.parseMulti("\"Albert Shift\" <a@albertshift.com>, 'Albert Shift' <a@albertshift.com>, ffffff", true);
		
		for (Recipient rec : list) {
			Assert.assertEquals("Albert Shift", rec.getName().get());
			Assert.assertEquals("a@albertshift.com", rec.getEmail());
		}
		
	}
	
}
