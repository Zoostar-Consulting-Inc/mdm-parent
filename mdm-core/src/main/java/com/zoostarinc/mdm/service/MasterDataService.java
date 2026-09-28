package com.zoostarinc.mdm.service;

import java.util.function.Supplier;

import com.zoostarinc.mdm.model.MasterData;

public interface MasterDataService<T> {
	MasterData<T> update(Supplier<MasterData<T>> supplier);
}
