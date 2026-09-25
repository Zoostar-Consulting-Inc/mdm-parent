package com.zoostarinc.mdm.service;

import java.util.function.Supplier;

import org.springframework.http.ResponseEntity;

import com.zoostarinc.mdm.model.MasterDataUUID;

public interface MasterDataService {
	ResponseEntity<MasterDataUUID> update(Supplier<MasterDataUUID> supplier);
}
