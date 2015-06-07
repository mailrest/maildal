package com.mailrest.maildal.model;

import com.noorq.casser.mapping.annotation.UDT;

@UDT
public interface Template {

	TemplateEngine engine();
	
	String fromRecipients();

	String bccRecipients();

	String subject();
	
	String textBody();

	String htmlBody();
	
}
