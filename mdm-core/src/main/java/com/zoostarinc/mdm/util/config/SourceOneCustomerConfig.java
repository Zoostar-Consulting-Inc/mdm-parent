package com.zoostarinc.mdm.util.config;

import com.zoostarinc.mdm.model.client.SourceOneCustomer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SourceOneCustomerConfig extends AbstractClientConfig {

	public static final String BASE_URL = "http://localhost:8080";

	public static final String CLIENT_ID = "sourceone";

	public static final String TYPE = "customer";
	
	public SourceOneCustomerConfig() {
		super(CLIENT_ID, TYPE, BASE_URL);
	}

	@Override
	public SourceOneCustomerMasterDataSupplier apply(String sourceId) {
		StringBuilder url = new StringBuilder(BASE_URL).append("/").append(CLIENT_ID).append("/").append(TYPE)
				.append("/").append(sourceId);
		log.info("This is where we would call url: {}", url.toString());

		// Data response from client
		return new SourceOneCustomerMasterDataSupplier(new SourceOneCustomer(sourceId, "ClientDataWithId" + sourceId));
	}

}
