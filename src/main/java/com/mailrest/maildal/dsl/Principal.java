package com.mailrest.maildal.dsl;

import java.util.UUID;

import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;

@Table
public interface Principal {

	@PartitionKey
	String email();

	String password();

	UUID accountId();

}
