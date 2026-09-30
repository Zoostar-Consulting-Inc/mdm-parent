package com.zoostarinc.mdm.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.zoostarinc.mdm.model.ClientConfigEntity;
import com.zoostarinc.mdm.model.MasterData;
import com.zoostarinc.mdm.service.ClientConfigService;
import com.zoostarinc.mdm.service.ClientDataService;
import com.zoostarinc.mdm.util.config.AbstractClientDataApplier;
import com.zoostarinc.mdm.util.config.SourceOneCustomerApplier;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@Service
@RequiredArgsConstructor
public class ClientDataUUIDService implements ClientDataService<UUID>, InitializingBean {

	private Map<String /* clientId */, Map<String /* type */, AbstractClientDataApplier>> registeredClients;

	private final ClientConfigService clientConfigManager;
	
	private final RestClient restClient;

	@Override
	public void afterPropertiesSet() throws Exception {
		initClientConfigs();
	}

	protected void initClientConfigs() {
		log.info("{}...", "Initializing Client Configurations");
		
		// This can also be configured in DB
		registeredClients = new HashMap<>();
		String clientId = "SOURCEONE";
		String type = "CUSTOMER";
		
		var registeredType = registeredClients.computeIfAbsent(clientId, k -> new HashMap<>());
		log.info("Registered {} for client {}.", type, clientId);
		registeredType.computeIfAbsent(type, k -> new SourceOneCustomerApplier(restClient));
		log.info("Registered config for client[{}]:type[{}]: {}" , clientId, type, registeredType.get(type));
	}

	@Override
	public MasterData<UUID> retrieve(MasterData<UUID> masterData) {
		ClientConfigEntity clientConfigEntity = clientConfigManager.retrieve(masterData.getClientId(), masterData.getType());
		
		var value = registeredClients.get(clientConfigEntity.getClientId());
		if (value == null) {
			throw new IllegalArgumentException("Unknown clientId: " + clientConfigEntity.getClientId());
		}
		
		var clientDataApplier = value.get(clientConfigEntity.getType());
		if (clientDataApplier == null) {
			throw new IllegalArgumentException("Unknown type: " + clientConfigEntity.getType());
		}
		
		return clientDataApplier.apply(masterData.getSourceId()).get();
	}

}
