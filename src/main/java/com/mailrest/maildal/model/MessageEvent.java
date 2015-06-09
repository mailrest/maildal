/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import java.util.UUID;

import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.Types;
import com.noorq.casser.mapping.annotation.UDT;

@UDT
public interface MessageEvent {

	@Types.Timeuuid
	@Constraints.NotNull
	UUID eventAt();
	
	@Constraints.NotNull
	MessageAction action();
	
}
