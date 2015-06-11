/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.secur;

import com.google.gson.JsonObject;

public interface JsonSerializable<T> {

	T newInstance();
	
	String verifyObj();
	
	String verifyJson(JsonObject jsonObject);
	
	void readJson(JsonObject jsonObject); 
	
	void writeJson(JsonObject jsonObject);
	
	
}
