package com.zoostarinc.mdm.service;

import java.util.UUID;

import com.zoostarinc.mdm.model.MasterData;

public interface ClientDataService {

	MasterData<UUID> retrieve(MasterData<UUID> masterData);
	
}
