/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.dsl;

import java.util.UUID;

import com.noorq.casser.mapping.annotation.UDT;

@UDT
public interface MessageEvent {

	UUID eventAt();
	
	EventType status();
	
}
