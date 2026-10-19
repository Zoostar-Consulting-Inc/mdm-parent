package com.zoostarinc.mdm.service;

import java.util.function.Supplier;

import org.springframework.http.ResponseEntity;

import com.zoostarinc.mdm.model.MasterData;

public interface MasterDataService {
	ResponseEntity<MasterData> update(Supplier<MasterData> supplier);
}
