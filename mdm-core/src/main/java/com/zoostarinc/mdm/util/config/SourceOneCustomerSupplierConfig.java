package com.zoostarinc.mdm.util.config;

import org.springframework.web.client.RestClient;

import com.zoostarinc.mdm.model.client.SourceOneCustomer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SourceOneCustomerSupplierConfig extends AbstractClientSupplierConfig {

	private static final String FWD_SLASH = "/";

	public SourceOneCustomerSupplierConfig(RestClient restClient) {
		super("SOURCEONE", "CUSTOMER", "http://localhost:8080", restClient);
	}

	@Override
	public SourceOneCustomerMasterDataSupplier apply(String sourceId) {
		StringBuilder url = new StringBuilder(super.getBaseUrl()).append(FWD_SLASH).append(super.getClientId())
				.append(FWD_SLASH).append(super.getType()).append(FWD_SLASH).append(sourceId);
		log.info("This is where we would call url: {}", url.toString());

		// Data response from client
		return new SourceOneCustomerMasterDataSupplier(new SourceOneCustomer(sourceId, "ClientDataWithId" + sourceId));
	}

}
