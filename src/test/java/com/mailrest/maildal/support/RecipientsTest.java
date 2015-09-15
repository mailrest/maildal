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
