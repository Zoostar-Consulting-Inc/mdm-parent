package com.zoostarinc.mdm.util.config;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.dao.EmptyResultDataAccessException;

import com.zoostarinc.mdm.model.client.SourceOneCustomer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class SourceOneCustomerSupplier implements Supplier<SourceOneCustomer> {

	private final Optional<String> hasId;
	
	@Override
	public SourceOneCustomer get() {
		log.info("Returning SourceOne Customer for id: {}", hasId);
		
		if(hasId.isEmpty() || "0".equals(hasId.get())) {
			throw new EmptyResultDataAccessException("No data found for given id!", 1);
		}
		var id = hasId.get();
		var value = new SourceOneCustomer(id);
		value.getStringProperties().put("Name", "Source One Customer with ID: " + id);
		return value;
	}

}
