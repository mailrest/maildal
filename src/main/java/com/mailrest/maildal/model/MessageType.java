/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

/**
 *  There are two types of the messages: incoming and outgoing 
 *
 *  Incoming message comes from the external service by SMTP
 *  Outgoing message creates by REST API and sends by MailRest
 *
 */

public enum MessageType {
	
	INCOMING, OUTGOING;
	
}
