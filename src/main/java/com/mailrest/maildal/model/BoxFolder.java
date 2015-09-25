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
	 *  
	 *  This folder id name is using in REST API interface
	 *  
	 *  @return folder id
	 */		
	
	@FolderId
	String folderId();
	
	/**
	 *  Actual display name of the folder
	 *  
	 *  For predefined folders it is the folderId with capitalized first letter
	 *  
	 *  This name is using in UI interface
	 *  
	 *  @return folder display name
	 */
	
	String folderName();
	
	
}
