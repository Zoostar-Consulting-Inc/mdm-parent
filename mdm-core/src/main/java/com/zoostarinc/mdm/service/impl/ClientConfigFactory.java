package com.zoostarinc.mdm.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.zoostarinc.mdm.model.MasterDataUUID;
import com.zoostarinc.mdm.util.config.AbstractClientConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientConfigFactory {

	private final Map<String /* clientId */, Map<String /* type */, AbstractClientConfig>> registeredClients = new HashMap<>();

	/**
	 * Register new types dynamically.
	 */
	public void registerClient(String clientId, String type, AbstractClientConfig clientConfig) {
		var registeredType = registeredClients.computeIfAbsent(clientId.toUpperCase(), k -> new HashMap<>());
		log.info("Registered {} for client {}", type.toUpperCase(), clientId.toUpperCase());
		registeredType.computeIfAbsent(type.toUpperCase(), k -> clientConfig);
	}

	/**
	 * The core factory method that retrieves data from remote client.
	 */
	public MasterDataUUID getClientData(String clientId, String type, String sourceId) {
		var value = registeredClients.get(clientId.toUpperCase());
		if (value == null) {
			throw new IllegalArgumentException("Unknown clientId: " + clientId);
		}
		
		var clientConfig = value.get(type.toUpperCase());
		if (clientConfig == null) {
			throw new IllegalArgumentException("Unknown type: " + type);
		}

		return clientConfig.apply(sourceId).get();
	}
}