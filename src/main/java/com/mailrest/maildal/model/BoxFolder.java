package com.mailrest.maildal.model;

import com.mailrest.maildal.model.constraint.FolderId;
import com.noorq.casser.mapping.annotation.UDT;

/**
 * Folder UDT is using to store information about box folders inside Box table
 * 
 * By default we have hard coded: inbox, drafts, sent, spam, trash folders that exists for all
 * 
 * User can create own folders with different filters (in future)
 * 
 * folderId is the folder name in lower case
 * 
 */

@UDT
public interface BoxFolder {

	/**
	 *  FolderId is calculated as a lower case of folder's display name
	 */		
	
	@FolderId
	String folderId();
	
	/**
	 *  Actual display name of the folder
	 */
	
	String folderName();
	
	
}