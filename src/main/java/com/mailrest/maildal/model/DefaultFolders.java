package com.mailrest.maildal.model;

/**
 *  Default folders are always exist in any Box
 *
 */

public enum DefaultFolders {

	INBOX("inbox"),
	DRAFTS("drafts"),
	SENT("sent"),
	SPAM("spam"),
	TRASH("trash");
	
	/**
	 * Name that uses in REST API
	 */
	
	private final String name;
	
	private DefaultFolders(String name) {
		this.name = name;
	}

	/**
	 * getName function
	 *  
	 * @return name of the folder for REST API
	 */
	
	public String getName() {
		return name;
	}
	
}
