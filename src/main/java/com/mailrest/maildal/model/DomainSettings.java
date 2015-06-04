package com.mailrest.maildal.model;

import com.noorq.casser.mapping.annotation.UDT;

@UDT
public interface DomainSettings {

	boolean addUnsubscribeLink();
	
	String unsubscribeText();
	
	String unsubscribeHtml();
	
}
