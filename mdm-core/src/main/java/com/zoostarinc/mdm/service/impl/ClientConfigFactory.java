package com.zoostarinc.mdm.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.zoostarinc.mdm.model.MasterDataUUID;
import com.zoostarinc.mdm.model.client.ClientData;
import com.zoostarinc.mdm.util.config.ClientConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientConfigFactory {

	private final Map<String, Map<String, ClientConfig<? extends ClientData>>> registeredClients = new HashMap<>();

	/**
	 * Register new types dynamically.
	 */
	public void registerClient(String clientId, String type, ClientConfig<? extends ClientData> clientConfig) {
		var registeredType = registeredClients.computeIfAbsent(clientId.toUpperCase(), k -> new HashMap<>());
		var config = registeredType.computeIfAbsent(type.toUpperCase(), k -> clientConfig);
		log.info("Registered {} for {} having data type: {}", type.toUpperCase(), clientId.toUpperCase(), config.getClientDataType());
	}

	/**
	 * The core factory method that retrieves data from remote client.
	 */
	public MasterDataUUID getClientData(String clientId, String type, String sourceId) {
		var clientConfig = registeredClients.get(clientId.toUpperCase()).get(type.toUpperCase());
		if (clientConfig == null) {
			throw new IllegalArgumentException("Unknown type: " + type);
		}
		return clientConfig.apply(sourceId);
	}
}