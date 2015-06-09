package com.mailrest.maildal.model;

import com.noorq.casser.mapping.annotation.UDT;

@UDT
public interface DomainSettings {

	String testToRecipients();

	String testBccRecipients();

	String prodBccRecipients();

	UnsubscribeOptions unsubscribeLink();
	
	String unsubscribeText();
	
	String unsubscribeHtml();
	
	TrackingOptions trackClicks();
	
	TrackingOptions trackOpens();
	
}
