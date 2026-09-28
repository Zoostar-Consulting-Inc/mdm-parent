package com.zoostarinc.mdm.service;

import com.zoostarinc.mdm.model.MasterData;

public interface ClientDataService<T> {

	MasterData<T> retrieve(MasterData<T> masterData);
	
}
