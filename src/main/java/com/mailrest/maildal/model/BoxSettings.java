package com.mailrest.maildal.model;

import com.noorq.casser.mapping.annotation.UDT;

/**
 *   Box settings are using to store information about enabled features per box 
 *
 */

@UDT
public interface BoxSettings {

	/**
	 *  IMAP enable or disable option 
	 *  
	 *  Default value is NO
	 *  
	 *  @return IMAP options
	 */
	
	ImapOptions imap();
		
}
