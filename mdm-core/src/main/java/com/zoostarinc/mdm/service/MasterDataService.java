package com.zoostarinc.mdm.service;

import java.util.function.Supplier;

import com.zoostarinc.mdm.model.MasterData;

public interface MasterDataService {
	MasterData update(Supplier<MasterData> supplier);
}
