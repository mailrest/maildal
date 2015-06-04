package com.mailrest.maildal.model;

import com.noorq.casser.mapping.annotation.UDT;

@UDT
public interface DomainSettings {

	String testRecipient();
	
	boolean addUnsubscribeLink();
	
	String unsubscribeText();
	
	String unsubscribeHtml();
	
	TrackingOptions trackingClicks();
	
	TrackingOptions trackingOpens();
	
}
