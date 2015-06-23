/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.repository;

import static com.noorq.casser.core.Query.eq;
import scala.Option;
import scala.concurrent.Future;

import com.datastax.driver.core.ResultSet;
import com.mailrest.maildal.model.Box;
import com.mailrest.maildal.model.BoxFolder;
import com.noorq.casser.core.Casser;

public interface BoxRepository extends AbstractRepository {

	static final Box box = Casser.dsl(Box.class);
	
	default Future<Option<Box>> findBox(BoxRef boxRef) {
		
		return session()
				.select(Box.class)
				.where(box::accountId, eq(boxRef.accountId()))
				.and(box::domainId, eq(boxRef.domainId()))
				.and(box::boxId, eq(boxRef.boxId()))
				.single()
				.future();
		
	}
	
	
	default Future<ResultSet> createBox(Box newBox) {
		
		return session()
			.insert(newBox)
			.future();
		
	}
	
	default Future<Option<BoxFolder>> findBoxFolder(FolderRef folderRef) {
		
		return session()
				.select(box::folders)
				.where(box::accountId, eq(folderRef.accountId()))
				.and(box::domainId, eq(folderRef.domainId()))
				.and(box::boxId, eq(folderRef.boxId()))
				.single()
				.map(t -> t._1.get(folderRef.folderId()))
				.future();
		
	}
	
	default Future<ResultSet> putBoxFolder(BoxRef boxRef, BoxFolder folder) {
		
		return session()
				.update()
				.put(box::folders, folder.folderId(), folder)
				.where(box::accountId, eq(boxRef.accountId()))
				.and(box::domainId, eq(boxRef.domainId()))
				.and(box::boxId, eq(boxRef.boxId()))				
				.future();
		
	}
	
	default Future<ResultSet> removeBoxFolder(FolderRef folderRef) {
		
		return session()
				.update()
				.put(box::folders, folderRef.folderId(), null)
				.where(box::accountId, eq(folderRef.accountId()))
				.and(box::domainId, eq(folderRef.domainId()))
				.and(box::boxId, eq(folderRef.boxId()))
				.future();
		
	}
	
	default Future<ResultSet> dropBox(BoxRef boxRef) {
		
		return session()
				.delete()
				.where(box::accountId, eq(boxRef.accountId()))
				.and(box::domainId, eq(boxRef.domainId()))
				.and(box::boxId, eq(boxRef.boxId()))
				.future();
		
	}
	

}
