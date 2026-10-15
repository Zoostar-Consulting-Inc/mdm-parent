package com.zoostarinc.mdm.util.config;

import java.util.Optional;
import java.util.function.Supplier;

import com.zoostarinc.mdm.model.client.SourceOneCustomer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class SourceOneCustomerSupplier implements Supplier<SourceOneCustomer> {

	private final Optional<String> id;
	
	@Override
	public SourceOneCustomer get() {
		if(id.isEmpty()) {
			throw new IllegalArgumentException("id is required!");
		}
		
		log.info("Returning SourceOne Customer for id: {}", id);
		var value = new SourceOneCustomer(id.get());
		value.getStringProperties().put("Name", "Source One Customer with ID: " + id);
		return value;
	}

}
