package com.mailrest.maildal.support;

import com.mailrest.maildal.model.Message;
import com.mailrest.maildal.model.MessageHeaders;

public final class MessageToHeadersAdapter implements MessageHeaders {

	private final Message message;
	
	public MessageToHeadersAdapter(Message message) {
		this.message = message;
	}

	@Override
	public String fromRecipient() {
		return message.fromRecipient();
	}

	@Override
	public String toRecipients() {
		return message.toRecipients();
	}

	@Override
	public String ccRecipients() {
		return message.ccRecipients();
	}

	@Override
	public String bccRecipients() {
		return message.bccRecipients();
	}

	@Override
	public String subject() {
		return message.subject();
	}
	
}
