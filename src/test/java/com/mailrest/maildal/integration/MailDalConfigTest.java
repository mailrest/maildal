package com.mailrest.maildal.integration;

import org.junit.Test;

import com.mailrest.maildal.config.MailDalConfig;

public class MailDalConfigTest {

	@Test
	public void test() {
		
		MailDalConfig config = new MailDalConfig("localhost", "mailrest");
		
		System.out.println(config.session());
		
		config.session().close();
		
	}
	
}
