package com.zoostarinc.mdm.service.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.zoostarinc.mdm.model.MasterDataUUID;
import com.zoostarinc.mdm.service.ClientDataService;
import com.zoostarinc.mdm.util.config.SourceOneCustomerConfig;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@Service
@RequiredArgsConstructor
public class DefaultClientDataService implements ClientDataService, InitializingBean {

	private ClientConfigFactory clientConfigFactory;

	@Override
	public MasterDataUUID retrieve(String clientId, String type, String sourceId) {
		var clientData = clientConfigFactory.getClientData(clientId, type, sourceId);
		if (clientData == null) {
			throw new IllegalArgumentException(String.format("No data found for given clientId: %s.", clientId));
		}
		return clientData;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		initClientConfigFactory();
	}

	protected void initClientConfigFactory() {
		log.info("{}...", "Initializing Client Config Factory");
		clientConfigFactory = new ClientConfigFactory();
		clientConfigFactory.registerClient("SOURCEONE", "CUSTOMER", new SourceOneCustomerConfig()); // This can also be configured in DB
	}

}
