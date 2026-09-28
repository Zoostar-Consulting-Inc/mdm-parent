package com.zoostarinc.mdm.service;

import java.util.UUID;
import java.util.function.Supplier;

import com.zoostarinc.mdm.model.MasterData;

public interface MasterDataService {
	MasterData<UUID> update(Supplier<MasterData<UUID>> supplier);
}
