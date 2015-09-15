/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.support;

import java.util.Optional;

import com.noorq.casser.mapping.validator.EmailValidator;


public final class Recipient {

	private final static EmailValidator VALIDATOR = new EmailValidator();
	
	private final Optional<String> name;
	private final String email;
	
	public Recipient(String nameOrNull, String email) {
		
		this.name = nameOrNull != null ? Optional.of(nameOrNull) : Optional.empty();
		
		if (email == null) {
			throw new IllegalArgumentException("empty email");
		}
		
		if (!VALIDATOR.isValid(email, null)) {
			throw new InvalidEmailException(email);
		}
		
		this.email = email;
	}

	public Optional<String> getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recipient other = (Recipient) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Recipient [name=" + name + ", email=" + email + "]";
	}
	
}
