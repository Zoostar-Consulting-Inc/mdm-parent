package com.zoostarinc.mdm.service;

import com.zoostarinc.mdm.model.ClientConfigEntity;

public interface ClientConfigService {

	default ClientConfigEntity retrieve(String clientId, String type) {
		return new ClientConfigEntity(clientId, type, "https://api.source");
	}

}
