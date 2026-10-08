package com.zoostarinc.mdm.util.config;

import java.util.function.Supplier;

import com.zoostarinc.mdm.model.client.SourceOneCustomer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class SourceOneCustomerSupplier implements Supplier<SourceOneCustomer> {

	private final String id;
	
	@Override
	public SourceOneCustomer get() {
		log.info("Returning SourceOne Customer for id: {}", id);
		return new SourceOneCustomer(id, "Source One Customer with ID: " + id);
	}

}
