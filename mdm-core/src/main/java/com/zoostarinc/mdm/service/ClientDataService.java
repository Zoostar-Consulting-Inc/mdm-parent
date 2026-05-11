package com.zoostarinc.mdm.service;

import com.zoostarinc.mdm.model.MasterDataUUID;

public interface ClientDataService {
	MasterDataUUID retrieve(String clientId, String type, String sourceId);
}
